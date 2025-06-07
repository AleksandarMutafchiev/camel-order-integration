Order Service Integration (Quarkus + Camel)

This multi-module Maven project provides an end-to-end Apache Camel integration service on Quarkus, exposing a REST API for processing orders, transforming data, and persisting to H2.

Project Structure

bom/
  pom.xml                ─ bill of materials (versions, dependencyManagement)
order-service/
  container/
    pom.xml              ─ Quarkus+Camel startup, routes, application.properties, Dockerfile
    src/main/java/...    ─ Camel `RouteBuilder` and configuration
  domain/
    pom.xml              ─ business models, services, MapStruct mappers
  data-access/
    pom.xml              ─ Panache repository, JPA entities
  rest-api/
    pom.xml              ─ JAX-RS resources (controllers)
  openapi/
    pom.xml              ─ OpenAPI DTO generation (openapi.yaml + generator plugin)
  pom.xml                ─ parent aggregator

Prerequisites

Java 21

Maven 3.8+

Docker (for container image)

(Optional) Kubernetes cluster

Building

# At repository root
mvn clean install -DskipTests

Running Locally

cd order-service/container
mvn quarkus:dev
# REST endpoints available at http://localhost:8080/api/orders
# Swagger UI at http://localhost:8080/q/swagger-ui

Packaging

cd order-service/container
mvn package -DskipTests
java -jar target/*-runner.jar

Docker Image

docker build -f container/Dockerfile -t order-service:latest .
docker run -p 8080:8080 order-service:latest

Kubernetes Deployment

Place deployment.yaml (in order-service/container/k8s/) alongside manifests:

apiVersion: apps/v1
kind: Deployment
metadata: { name: order-service }
spec:
  replicas: 1
  template:
    spec:
      containers:
        - name: order-service
          image: order-service:latest
          ports: [{ containerPort: 8080 }]
          env: # you may add ENV vars here

Apply:

kubectl apply -f order-service/container/k8s/deployment.yaml

Endpoints

POST /api/orders - submit Order JSON

GET  /api/orders/{id} - fetch processed order status

Swagger UI: /q/swagger-ui

Monitoring & Health

Quarkus adds:

Health: /q/health

Metrics: /q/metrics

Camel metrics available under JMX or Prometheus endpoints.

© 2025


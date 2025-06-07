package org.example.service.route;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OrderRouteTest {

    @Test
    public void testOrderRoute() {
        String payload = "{\"orderId\":\"ORD-001\",\"customer\":{\"id\":\"CUST-123\",\"name\":\"Alice\",\"email\":\"alice@example.com\"},\"items\":[{\"productId\":\"P1\",\"quantity\":2,\"unitPrice\":10.5}],\"orderDate\":\"2025-06-01T10:00:00Z\"}";

        given()
            .contentType(ContentType.JSON)
            .body(payload)
        .when()
            .post("/api/orders")
        .then()
            .statusCode(200)
            .body("status", is("RECEIVED"));
    }
}

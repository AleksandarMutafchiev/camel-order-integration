package route;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import models.Order;
import org.apache.camel.builder.RouteBuilder;
import repostitory.OrderRepository;
import transformer.OrderTransformer;
import validator.OrderValidator;

import java.util.logging.Logger;

@ApplicationScoped
public class OrderRoute extends RouteBuilder {

    private static final Logger LOG = Logger.getLogger(String.valueOf(OrderRoute.class));

    private final OrderValidator validator;

    private final OrderTransformer transformer;

    private final OrderRepository repository;

    @Inject
    public OrderRoute(OrderValidator validator, OrderTransformer transformer, OrderRepository repository) {
        this.validator = validator;
        this.transformer = transformer;
        this.repository = repository;
    }

    @Override
    public void configure() {
        onException(Exception.class)
            .handled(true)
            .log("[ERROR] ${exception.message}")
            .setHeader("CamelHttpResponseCode", constant(400))
            .setBody(simple("{\"error\": \"${exception.message}\"}"));

        restConfiguration().contextPath("/api");

        rest("/orders")
            .post()
            .type(Order.class)
            .consumes("application/json")
            .produces("application/json")
            .to("direct:processOrder");

        from("direct:processOrder")
            .routeId("order-processing")
            .log("Received order: ${body.orderId}")
            .bean(validator, "validate")
            .bean(transformer, "toXml")
            .log("Transformed XML: ${body}")
            .bean(repository, "saveOrder")
            .setBody(simple("{\"reference\": \"${header.orderId}\", \"status\": \"RECEIVED\"}"))
            .setHeader("Content-Type", constant("application/json"));
    }
}

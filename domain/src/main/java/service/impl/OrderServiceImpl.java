
package service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import models.Order;
import org.apache.camel.ProducerTemplate;
import org.jboss.logging.Logger;

import repostitory.OrderRepository;
import service.OrderService;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

    private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);

    @Inject
    ProducerTemplate producerTemplate;

    @Inject
    OrderRepository orderRepository;

    public Order process(Order order) {
        LOG.info("Processing order ID: " + order.getOrderId());
        producerTemplate.sendBody("direct:process-order", order);
        create(order);
        return order;
    }

    @Override
    public Order get(UUID uuid) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        return List.of();
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return null;
    }
}

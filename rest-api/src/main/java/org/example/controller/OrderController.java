package org.example.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import models.Order;
import org.example.api.OrdersApi;
import org.example.dto.OrderDto;
import org.example.mapper.OrderMapper;
import org.jboss.logging.Logger;
import service.OrderService;

import java.util.UUID;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderController implements OrdersApi {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private static final Logger LOG = Logger.getLogger(OrderController.class);

    @Inject
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }


    @Override
    public OrderDto ordersIdGet(UUID id) {
        return null;
    }

    @Override
    public OrderDto ordersPost(OrderDto orderDto) {
        Order order = orderService.process(orderMapper.toModel(orderDto));
        return orderMapper.toDto(order);
    }
}
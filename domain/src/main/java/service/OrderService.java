package service;

import models.Order;

import java.util.UUID;

public interface OrderService extends Service<Order, UUID>{

    public Order process(Order order);
}

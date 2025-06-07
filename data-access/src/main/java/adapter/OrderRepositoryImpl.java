package adapter;

import entity.OrderEntity;
import exception.NotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mapper.OrderMapper;
import models.Order;
import repository.OrderPanacheRepository;
import repostitory.OrderRepository;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderPanacheRepository orderRepository;

    private final OrderMapper mapper;

    @Inject
    public OrderRepositoryImpl(OrderPanacheRepository orderRepository, OrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Order> findById(UUID uuid) {
        return orderRepository.findByIdOptional(uuid).map(mapper::toModel);
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = mapper.toEntity(order);

        orderRepository.persist(entity);
        return mapper.toModel(entity);
    }

    @Override
    public Order update(Order order) {
        OrderEntity entity = orderRepository.findByIdOptional(order.getOrderId()).orElseThrow(() ->  new NotFoundException(
                String.format(
                        "Order with id: %scould not be found!", order.getOrderId())));
        orderRepository.persist(entity);

        return null;
    }

    @Override
    public boolean delete(UUID uuid) {
        return false;
    }

    @Override
    public void deleteAll() {

    }
}

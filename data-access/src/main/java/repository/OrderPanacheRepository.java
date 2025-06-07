package repository;

import entity.OrderEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import models.Order;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class OrderPanacheRepository implements PanacheRepositoryBase<OrderEntity, UUID> {

}

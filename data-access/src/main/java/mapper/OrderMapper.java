package mapper;

import entity.OrderEntity;
import models.Order;
import org.mapstruct.Mapper;

/**
 * Defines a mapper interface for mapping AIMSLReconciliationAerodromeCode to their corresponding
 * entities and vice versa.
 */
@Mapper(
        uses = {ItemMapper.class},
        componentModel = "jakarta")
public interface OrderMapper {

    OrderEntity toEntity(Order order);

    Order toModel(OrderEntity orderEntity);
}

package mapper;

import entity.ItemEntity;
import entity.OrderEntity;
import models.Item;
import models.Order;
import org.mapstruct.Mapper;

/**
 * Defines a mapper interface for mapping AIMSLReconciliationAerodromeCode to their corresponding
 * entities and vice versa.
 */
@Mapper(componentModel = "cdi")
public interface ItemMapper {

    ItemEntity toEntity(Item order);

    Item toModel(ItemEntity customerEntity);
}

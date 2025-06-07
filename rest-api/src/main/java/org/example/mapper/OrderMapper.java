package org.example.mapper;

import models.Customer;
import models.Order;
import org.example.dto.OrderDto;
import org.mapstruct.*;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

/**
 * Defines a mapper interface for mapping AIMSLReconciliationAerodromeCode to their corresponding
 * entities and vice versa.
 */
@Mapper(
        componentModel = "jakarta",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface OrderMapper {

    @Mapping(source = "order.customer", target = "customer")
    @Mapping(source = "orderDate", target = "orderDate", qualifiedByName = "zonedToOffset")
    OrderDto toDto(Order order);

    @Mapping(source = "dto.customer", target = "customer")
    @Mapping(source = "orderDate", target = "orderDate", qualifiedByName = "offsetToZoned")
    Order toModel(OrderDto dto);

    /* --- Customer мапинг --- */

    @Named("entityToDtoCustomer")
    static Customer entityToDtoCustomer(Customer e) {
        if (e == null) return null;
        Customer dto = new Customer();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setEmail(e.getEmail());
        return dto;
    }

    @Named("dtoToEntityCustomer")
    static Customer dtoToEntityCustomer(Customer dto) {
        if (dto == null) return null;
        Customer e = new Customer();
        e.setId(dto.getId());
        e.setName(dto.getName());
        e.setEmail(dto.getEmail());
        return e;
    }

    /* --- Date мапинг --- */

    @Named("zonedToOffset")
    static OffsetDateTime zonedToOffset(ZonedDateTime zdt) {
        return zdt == null ? null : zdt.toOffsetDateTime();
    }

    @Named("offsetToZoned")
    static ZonedDateTime offsetToZoned(OffsetDateTime odt) {
        return odt == null ? null : odt.toZonedDateTime();
    }
}

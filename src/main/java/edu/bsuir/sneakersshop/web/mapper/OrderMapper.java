package edu.bsuir.sneakersshop.web.mapper;

import edu.bsuir.sneakersshop.domain.entity.Order;
import edu.bsuir.sneakersshop.web.model.OrderModel;
import org.mapstruct.Mapper;

@Mapper(uses = OrderItemMapper.class)
public interface OrderMapper {
    OrderModel mapToModel(Order order);
}

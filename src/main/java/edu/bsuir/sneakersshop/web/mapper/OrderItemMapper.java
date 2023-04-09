package edu.bsuir.sneakersshop.web.mapper;

import edu.bsuir.sneakersshop.domain.entity.OrderItem;
import edu.bsuir.sneakersshop.web.model.OrderItemModel;
import org.mapstruct.Mapper;

@Mapper(uses = ProductMapper.class)
public interface OrderItemMapper {
    OrderItemModel mapToModel(OrderItem orderItem);
}

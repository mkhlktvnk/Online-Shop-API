package edu.bsuir.sneakersshop.web.mapper;

import edu.bsuir.sneakersshop.domain.entity.Order;
import edu.bsuir.sneakersshop.web.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(uses = ProductMapper.class)
public interface OrderMapper {
    @Mapping(target = "productModel", source = "product")
    OrderModel mapToModel(Order order);

    List<OrderModel> mapToModel(Collection<Order> orders);
}

package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Order;
import edu.bsuir.onlineshop.web.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {
        ProductMapper.class,
        UserMapper.class
})
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "productModel", source = "product"),
            @Mapping(target = "userModel", source = "user")
    })
    OrderModel mapToModel(Order order);

}

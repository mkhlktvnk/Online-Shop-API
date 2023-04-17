package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Order;
import edu.bsuir.onlineshop.web.controller.OrderController;
import edu.bsuir.onlineshop.web.controller.ProductController;
import edu.bsuir.onlineshop.web.controller.UserController;
import edu.bsuir.onlineshop.web.model.OrderModel;
import org.mapstruct.*;
import org.springframework.http.HttpMethod;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    List<OrderModel> mapToModel(Collection<Order> orders);

    @AfterMapping
    default void addLinks(@MappingTarget OrderModel orderModel) {
        orderModel.add(
                linkTo(methodOn(OrderController.class)
                        .findById(orderModel.getId()))
                        .withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(ProductController.class)
                        .getProductById(orderModel.getProductModel().getId()))
                        .withRel("product")
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(UserController.class)
                        .findByUsername(orderModel.getUserModel().getUsername()))
                        .withRel("customer")
                        .withType(HttpMethod.GET.name())
        );
    }

}

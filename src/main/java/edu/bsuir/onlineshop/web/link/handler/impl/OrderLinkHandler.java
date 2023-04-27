package edu.bsuir.onlineshop.web.link.handler.impl;

import edu.bsuir.onlineshop.web.controller.OrderController;
import edu.bsuir.onlineshop.web.controller.ProductController;
import edu.bsuir.onlineshop.web.controller.UserController;
import edu.bsuir.onlineshop.web.link.handler.LinkHandler;
import edu.bsuir.onlineshop.web.model.OrderModel;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderLinkHandler extends LinkHandler<OrderModel> {

    @Override
    protected void addCommonLinks(OrderModel model) {
        model.add(
                linkTo(methodOn(OrderController.class)
                        .findById(model.getId()))
                        .withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(ProductController.class)
                        .getProductById(model.getProductModel().getId()))
                        .withRel("product")
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(UserController.class)
                        .findByUsername(model.getUserModel().getUsername()))
                        .withRel("customer")
                        .withType(HttpMethod.GET.name())
        );
    }

    @Override
    protected void addUserRelatedLinks(OrderModel model) {

    }

    @Override
    protected void addAdminRelatedLinks(OrderModel model) {

    }

}

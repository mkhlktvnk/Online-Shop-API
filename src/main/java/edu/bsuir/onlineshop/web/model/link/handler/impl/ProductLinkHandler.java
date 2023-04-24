package edu.bsuir.onlineshop.web.model.link.handler.impl;

import edu.bsuir.onlineshop.web.controller.CategoryController;
import edu.bsuir.onlineshop.web.controller.OrderController;
import edu.bsuir.onlineshop.web.controller.ProductController;
import edu.bsuir.onlineshop.web.controller.ReviewController;
import edu.bsuir.onlineshop.web.model.ProductModel;
import edu.bsuir.onlineshop.web.model.link.handler.LinkHandler;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductLinkHandler extends LinkHandler<ProductModel> {

    @Override
    protected void addCommonLinks(ProductModel model) {
        model.add(
                linkTo(methodOn(ProductController.class)
                        .getProductById(model.getId()))
                        .withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(CategoryController.class)
                        .findAllByProductId(model.getId(), null))
                        .withRel("categories")
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(ReviewController.class)
                        .findAllByProductId(model.getId(), null))
                        .withRel("reviews")
                        .withType(HttpMethod.GET.name())
        );
    }

    @Override
    protected void addUserRelatedLinks(ProductModel model) {
        model.add(
                linkTo(methodOn(OrderController.class)
                        .makeOrder(null, null))
                        .withRel("makeOrder")
                        .withType(HttpMethod.POST.name()),
                linkTo(methodOn(ReviewController.class)
                        .makeReview(null, model.getId(), null))
                        .withRel("makeReview")
                        .withType(HttpMethod.POST.name())
        );
    }

    @Override
    protected void addAdminRelatedLinks(ProductModel model) {
        model.add(
                linkTo(methodOn(ProductController.class)
                        .insertProduct(model))
                        .withRel("create")
                        .withType(HttpMethod.POST.name()),
                linkTo(methodOn(ProductController.class))
                        .slash("products")
                        .slash(model.getId())
                        .withRel("update")
                        .withType(HttpMethod.PUT.name()),
                linkTo(methodOn(ProductController.class))
                        .slash("products")
                        .slash(model.getId())
                        .withRel("delete")
                        .withType(HttpMethod.DELETE.name())
        );
    }

}

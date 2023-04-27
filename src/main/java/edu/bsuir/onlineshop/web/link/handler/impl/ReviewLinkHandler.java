package edu.bsuir.onlineshop.web.link.handler.impl;

import edu.bsuir.onlineshop.web.controller.ProductController;
import edu.bsuir.onlineshop.web.controller.ReviewController;
import edu.bsuir.onlineshop.web.controller.UserController;
import edu.bsuir.onlineshop.web.link.handler.LinkHandler;
import edu.bsuir.onlineshop.web.model.ReviewModel;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ReviewLinkHandler extends LinkHandler<ReviewModel> {

    @Override
    protected void addCommonLinks(ReviewModel model) {
        model.add(
                linkTo(methodOn(ReviewController.class)
                        .findById(model.getId()))
                        .withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(ProductController.class)
                        .getProductById(model.getProductModel().getId()))
                        .withRel("product"),
                linkTo(methodOn(UserController.class)
                        .findByUsername(model.getUserModel().getUsername()))
                        .withRel("author")
        );
    }

    @Override
    protected void addUserRelatedLinks(ReviewModel model) {
        model.add(
                linkTo(methodOn(ReviewController.class))
                        .slash("users")
                        .slash(model.getUserModel().getId())
                        .slash("reviews")
                        .withRel("update")
                        .withType(HttpMethod.PUT.name()),
                linkTo(methodOn(ReviewController.class))
                        .slash("users")
                        .slash(model.getUserModel().getId())
                        .slash("reviews")
                        .withRel("delete")
                        .withType(HttpMethod.DELETE.name())
        );
    }

    @Override
    protected void addAdminRelatedLinks(ReviewModel model) {

    }

}

package edu.bsuir.onlineshop.web.link.handler.impl;

import edu.bsuir.onlineshop.web.controller.CategoryController;
import edu.bsuir.onlineshop.web.controller.ProductController;
import edu.bsuir.onlineshop.web.link.handler.LinkHandler;
import edu.bsuir.onlineshop.web.model.CategoryModel;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CategoryLinkHandler extends LinkHandler<CategoryModel> {

    @Override
    protected void addCommonLinks(CategoryModel model) {
        model.add(
                linkTo(methodOn(CategoryController.class)
                        .findById(model.getId()))
                        .withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(ProductController.class)
                        .findAllByCategoryId(model.getId(), null))
                        .withRel("products")
                        .withType(HttpMethod.GET.name())
        );
    }

    @Override
    protected void addUserRelatedLinks(CategoryModel model) {

    }

    @Override
    protected void addAdminRelatedLinks(CategoryModel model) {
        model.add(
                linkTo(methodOn(CategoryController.class)
                        .save(model))
                        .withRel("create")
                        .withType(HttpMethod.POST.name()),
                linkTo(methodOn(CategoryController.class))
                        .slash("categories")
                        .slash(model.getId())
                        .withRel("update")
                        .withType(HttpMethod.PUT.name()),
                linkTo(methodOn(CategoryController.class))
                        .slash("categories")
                        .slash(model.getId())
                        .withRel("delete")
                        .withType(HttpMethod.DELETE.name())
        );
    }

}

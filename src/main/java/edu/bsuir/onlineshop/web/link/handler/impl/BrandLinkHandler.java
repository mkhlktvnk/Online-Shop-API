package edu.bsuir.onlineshop.web.link.handler.impl;

import edu.bsuir.onlineshop.web.controller.BrandController;
import edu.bsuir.onlineshop.web.link.handler.LinkHandler;
import edu.bsuir.onlineshop.web.model.BrandModel;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BrandLinkHandler extends LinkHandler<BrandModel> {

    @Override
    protected void addCommonLinks(BrandModel model) {
        model.add(
                linkTo(methodOn(BrandController.class)
                        .getBrandById(model.getId()))
                        .withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
    }

    @Override
    protected void addUserRelatedLinks(BrandModel model) {

    }

    @Override
    protected void addAdminRelatedLinks(BrandModel model) {
        model.add(
                linkTo(methodOn(BrandController.class)
                        .insertBrand(model))
                        .withRel("create")
                        .withType(HttpMethod.POST.name()),
                linkTo(methodOn(BrandController.class))
                        .slash("brands")
                        .slash(model.getId())
                        .withRel("update")
                        .withType(HttpMethod.PUT.name()),
                linkTo(methodOn(BrandController.class))
                        .slash("brands")
                        .slash(model.getId())
                        .withRel("delete")
                        .withType(HttpMethod.DELETE.name())
        );
    }

}

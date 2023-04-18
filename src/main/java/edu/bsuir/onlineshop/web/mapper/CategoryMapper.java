package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Category;
import edu.bsuir.onlineshop.web.controller.CategoryController;
import edu.bsuir.onlineshop.web.controller.ProductController;
import edu.bsuir.onlineshop.web.model.CategoryModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.http.HttpMethod;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper
public interface CategoryMapper {
    CategoryModel mapToModel(Category category);

    Category mapToEntity(CategoryModel categoryModel);

    @AfterMapping
    default void addLinks(@MappingTarget CategoryModel categoryModel) {
        categoryModel.add(
                linkTo(methodOn(CategoryController.class)
                        .findById(categoryModel.getId()))
                        .withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(CategoryController.class)
                        .save(categoryModel))
                        .withRel("create")
                        .withType(HttpMethod.POST.name()),
                linkTo(methodOn(CategoryController.class))
                        .slash("categories")
                        .slash(categoryModel.getId())
                        .withRel("update")
                        .withType(HttpMethod.PUT.name()),
                linkTo(methodOn(CategoryController.class))
                        .slash("categories")
                        .slash(categoryModel.getId())
                        .withRel("delete")
                        .withType(HttpMethod.DELETE.name()),
                linkTo(methodOn(ProductController.class)
                        .findAllByCategoryId(categoryModel.getId(), null))
                        .withRel("products")
                        .withType(HttpMethod.GET.name())
        );
    }
}

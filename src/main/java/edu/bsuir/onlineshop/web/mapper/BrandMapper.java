package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Brand;
import edu.bsuir.onlineshop.web.controller.BrandController;
import edu.bsuir.onlineshop.web.controller.ProductController;
import edu.bsuir.onlineshop.web.model.BrandModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.http.HttpMethod;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper
public interface BrandMapper {
    Brand mapToEntity(BrandModel brandModel);

    BrandModel mapToModel(Brand brand);

    List<Brand> mapToEntity(Collection<BrandModel> brandModels);

    List<BrandModel> mapToModel(Collection<Brand> brands);

    @AfterMapping
    default void addLinks(@MappingTarget BrandModel brandModel) {
        brandModel.add(
                linkTo(methodOn(BrandController.class)
                        .getBrandById(brandModel.getId()))
                        .withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(BrandController.class)
                        .insertBrand(brandModel))
                        .withRel("create"),
                linkTo(methodOn(BrandController.class))
                        .slash("brands")
                        .slash(brandModel.getId())
                        .withRel("update")
                        .withType(HttpMethod.PUT.name()),
                linkTo(methodOn(BrandController.class))
                        .slash("brands")
                        .slash(brandModel.getId())
                        .withRel("update")
                        .withType(HttpMethod.PUT.name()),
                linkTo(methodOn(BrandController.class))
                        .slash("brands")
                        .slash(brandModel.getId())
                        .withRel("delete")
                        .withType(HttpMethod.DELETE.name())
        );
    }
}

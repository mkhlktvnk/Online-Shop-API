package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Product;
import edu.bsuir.onlineshop.web.controller.CategoryController;
import edu.bsuir.onlineshop.web.controller.OrderController;
import edu.bsuir.onlineshop.web.controller.ProductController;
import edu.bsuir.onlineshop.web.controller.ReviewController;
import edu.bsuir.onlineshop.web.model.ProductModel;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.http.HttpMethod;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(uses = {
        ImageMapper.class,
        SizeMapper.class
})
public interface ProductMapper {
    Product mapToEntity(ProductModel productModel);

    ProductModel mapToModel(Product product);

    Collection<Product> mapToEntity(Collection<ProductModel> productModels);

    Collection<ProductModel> mapToModel(Collection<Product> products);

    @AfterMapping
    default void addLinks(@MappingTarget ProductModel productModel) {
        productModel.add(
                linkTo(methodOn(ProductController.class)
                        .getProductById(productModel.getId()))
                        .withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(ProductController.class)
                        .insertProduct(productModel))
                        .withRel("create")
                        .withType(HttpMethod.POST.name()),
                linkTo(methodOn(ProductController.class))
                        .slash("products")
                        .slash(productModel.getId())
                        .withRel("update")
                        .withType(HttpMethod.PUT.name()),
                linkTo(methodOn(ProductController.class))
                        .slash("products")
                        .slash(productModel.getId())
                        .withRel("delete")
                        .withType(HttpMethod.DELETE.name()),
                linkTo(methodOn(CategoryController.class)
                        .findAllByProductId(productModel.getId(), null))
                        .withRel("categories")
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(ReviewController.class)
                        .findAllByProductId(productModel.getId(), null))
                        .withRel("reviews")
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(OrderController.class)
                        .makeOrder(null, null))
                        .withRel("makeOrder")
                        .withType(HttpMethod.POST.name()),
                linkTo(methodOn(ReviewController.class)
                        .makeReview(null, productModel.getId(), null))
                        .withRel("makeReview")
                        .withType(HttpMethod.POST.name())
        );
    }
}

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
}

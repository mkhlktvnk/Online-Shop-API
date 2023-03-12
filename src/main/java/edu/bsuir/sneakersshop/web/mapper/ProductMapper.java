package edu.bsuir.sneakersshop.web.mapper;

import edu.bsuir.sneakersshop.domain.entity.Product;
import edu.bsuir.sneakersshop.web.model.ProductModel;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface ProductMapper {
    Product mapToEntity(ProductModel productModel);

    ProductModel mapToModel(Product product);

    Collection<Product> mapToEntity(Collection<ProductModel> productModels);

    Collection<ProductModel> mapToModel(Collection<Product> products);
}

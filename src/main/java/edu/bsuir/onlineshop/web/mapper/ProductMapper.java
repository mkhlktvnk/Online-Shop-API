package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Product;
import edu.bsuir.onlineshop.web.model.ProductModel;
import org.mapstruct.Mapper;

import java.util.Collection;

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

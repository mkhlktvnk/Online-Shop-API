package edu.bsuir.onlineshop.web.assembler;

import edu.bsuir.onlineshop.domain.entity.Product;
import edu.bsuir.onlineshop.web.link.handler.LinkHandler;
import edu.bsuir.onlineshop.web.mapper.ProductMapper;
import edu.bsuir.onlineshop.web.model.ProductModel;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductResourceAssembler implements RepresentationModelAssembler<Product, ProductModel> {
    private final LinkHandler<ProductModel> linkHandler;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public ProductModel toModel(Product entity) {
        ProductModel productModel = mapper.mapToModel(entity);
        linkHandler.addLinks(productModel);
        return productModel;
    }
}

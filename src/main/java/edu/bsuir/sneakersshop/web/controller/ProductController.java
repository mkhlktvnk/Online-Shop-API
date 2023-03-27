package edu.bsuir.sneakersshop.web.controller;

import edu.bsuir.sneakersshop.service.ProductService;
import edu.bsuir.sneakersshop.web.criteria.ProductCriteria;
import edu.bsuir.sneakersshop.web.mapper.ProductMapper;
import edu.bsuir.sneakersshop.web.model.ProductModel;
import edu.bsuir.sneakersshop.web.specification.ProductSpecification;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @GetMapping("/products")
    public List<ProductModel> getProducts(@PageableDefault Pageable pageable, ProductSpecification specification) {
        return (List<ProductModel>) mapper.mapToModel(productService.findAll(pageable, specification));
    }

    @GetMapping("/products/{id}")
    public ProductModel getProductById(@PathVariable Long id) {
        return mapper.mapToModel(productService.findOne(id));
    }

    @PostMapping("/products")
    public ProductModel insertProduct(@Valid @RequestBody ProductModel product) {
        return mapper.mapToModel(
                productService.insert(mapper.mapToEntity(product))
        );
    }

    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable Long id, @Valid @RequestBody ProductModel product) {
        productService.update(id, mapper.mapToEntity(product));
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}

package edu.bsuir.onlineshop.web.controller;

import edu.bsuir.onlineshop.domain.entity.Product;
import edu.bsuir.onlineshop.service.ProductService;
import edu.bsuir.onlineshop.web.criteria.ProductCriteria;
import edu.bsuir.onlineshop.web.mapper.ProductMapper;
import edu.bsuir.onlineshop.web.model.ProductModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final PagedResourcesAssembler<Product> pagedResourcesAssembler;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @GetMapping("/products")
    public PagedModel<ProductModel> findAllByPageableAndCriteria(
            @PageableDefault Pageable pageable, @Valid ProductCriteria criteria) {
        Page<Product> products = productService.findAll(pageable, criteria);
        return pagedResourcesAssembler.toModel(products, mapper::mapToModel);
    }

    @GetMapping("/categories/{categoryId}/products")
    public PagedModel<ProductModel> findAllByCategoryId(
            @PathVariable Long categoryId, @PageableDefault Pageable pageable) {
        Page<Product> products = productService.findAllByCategoryId(categoryId, pageable);
        return pagedResourcesAssembler.toModel(products, mapper::mapToModel);
    }

    @GetMapping("/products/{id}")
    public ProductModel getProductById(@PathVariable Long id) {
        return mapper.mapToModel(productService.findById(id));
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
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

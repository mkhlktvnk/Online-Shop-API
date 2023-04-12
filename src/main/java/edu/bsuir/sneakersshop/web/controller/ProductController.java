package edu.bsuir.sneakersshop.web.controller;

import edu.bsuir.sneakersshop.service.ProductService;
import edu.bsuir.sneakersshop.web.criteria.ProductCriteria;
import edu.bsuir.sneakersshop.web.mapper.ProductMapper;
import edu.bsuir.sneakersshop.web.model.ProductModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import java.util.List;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @GetMapping("/products")
    public List<ProductModel> getProducts(@PageableDefault Pageable pageable, @Valid ProductCriteria criteria) {
        return (List<ProductModel>) mapper.mapToModel(productService.findAll(pageable, criteria));
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

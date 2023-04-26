package edu.bsuir.onlineshop.web.controller;

import edu.bsuir.onlineshop.domain.entity.Category;
import edu.bsuir.onlineshop.service.CategoryService;
import edu.bsuir.onlineshop.web.criteria.CategoryCriteria;
import edu.bsuir.onlineshop.web.mapper.CategoryMapper;
import edu.bsuir.onlineshop.web.model.CategoryModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
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
public class CategoryController {
    private final CategoryService categoryService;
    private final PagedResourcesAssembler<Category> pagedResourcesAssembler;
    private final RepresentationModelAssembler<Category, CategoryModel> modelAssembler;
    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @GetMapping("/categories")
    public PagedModel<CategoryModel> findAllByPageableAndCriteria(
            @PageableDefault Pageable pageable, @Valid CategoryCriteria criteria) {
        Page<Category> categories = categoryService.findAll(pageable, criteria);
        return pagedResourcesAssembler.toModel(categories, modelAssembler);
    }

    @GetMapping("/products/{productId}/categories")
    public PagedModel<CategoryModel> findAllByProductId(
            @PathVariable Long productId, @PageableDefault Pageable pageable) {
        Page<Category> categories = categoryService.findAllByProductId(productId, pageable);
        return pagedResourcesAssembler.toModel(categories, modelAssembler);
    }

    @GetMapping("/categories/{id}")
    public CategoryModel findById(@PathVariable Long id) {
        return mapper.mapToModel(
                categoryService.findById(id)
        );
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryModel save(@Valid @RequestBody CategoryModel categoryModel) {
        Category category = mapper.mapToEntity(categoryModel);
        return modelAssembler.toModel(
                categoryService.insert(category)
        );
    }

    @PutMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody CategoryModel categoryModel) {
        categoryService.updateById(id, mapper.mapToEntity(categoryModel));
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}

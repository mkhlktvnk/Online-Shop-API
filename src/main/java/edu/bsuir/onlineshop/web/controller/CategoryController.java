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
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PagedModel<CategoryModel>> findAllByPageableAndCriteria(
            @PageableDefault Pageable pageable, @Valid CategoryCriteria criteria) {
        Page<Category> categories = categoryService.findAll(pageable, criteria);
        PagedModel<CategoryModel> pagedModel = pagedResourcesAssembler.toModel(categories, modelAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/products/{productId}/categories")
    public ResponseEntity<PagedModel<CategoryModel>> findAllByProductId(
            @PathVariable Long productId, @PageableDefault Pageable pageable) {
        Page<Category> categories = categoryService.findAllByProductId(productId, pageable);
        PagedModel<CategoryModel> pagedModel = pagedResourcesAssembler.toModel(categories, modelAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryModel> findById(@PathVariable Long id) {
        CategoryModel categoryModel = mapper.mapToModel(categoryService.findById(id));
        return ResponseEntity.ok(categoryModel);
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CategoryModel> save(@Valid @RequestBody CategoryModel categoryModel) {
        Category category = mapper.mapToEntity(categoryModel);
        Category savedCategory = categoryService.insert(category);
        CategoryModel savedCategoryModel = mapper.mapToModel(savedCategory);
        return ResponseEntity.ok(savedCategoryModel);
    }

    @PutMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody CategoryModel categoryModel) {
        Category category = mapper.mapToEntity(categoryModel);
        categoryService.updateById(id, category);
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}


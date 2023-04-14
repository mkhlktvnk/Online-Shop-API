package edu.bsuir.onlineshop.service;

import edu.bsuir.onlineshop.domain.entity.Category;
import edu.bsuir.onlineshop.web.criteria.CategoryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable, CategoryCriteria criteria);

    Page<Category> findAllByProductId(long productId, Pageable pageable);

    Category findById(long id);

    Category insert(Category category);

    void updateById(long id, Category updateCategory);

    void deleteById(long id);

    boolean existsById(long id);

}

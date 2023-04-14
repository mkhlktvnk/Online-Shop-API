package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Page<Category> findAllByProductId(long productId, Pageable pageable);

    Category findById(long id);

    Category insert(Category category);

    void updateById(long id, Category updateCategory);

    void deleteById(long id);

    boolean existsById(long id);

}

package edu.bsuir.onlineshop.service;

import edu.bsuir.onlineshop.domain.entity.Product;
import edu.bsuir.onlineshop.web.criteria.ProductCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product insert(Product product);

    void update(Long id, Product product);

    void delete(Long id);

    Product findById(Long id);

    Page<Product> findAll(Pageable pageable, ProductCriteria criteria);

    Page<Product> findAllByCategoryId(long categoryId, Pageable pageable);

    boolean existsById(Long id);
}

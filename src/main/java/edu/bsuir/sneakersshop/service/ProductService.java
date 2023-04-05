package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.Product;
import edu.bsuir.sneakersshop.web.criteria.ProductCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product insert(Product product);

    void update(Long id, Product product);

    void delete(Long id);

    Product findById(Long id);

    List<Product> findAll(Pageable pageable, ProductCriteria criteria);

    boolean isExistsById(Long id);
}

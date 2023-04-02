package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.Product;
import edu.bsuir.sneakersshop.domain.entity.enums.SeasonType;
import edu.bsuir.sneakersshop.web.criteria.ProductCriteria;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product insert(Product product);

    void update(Long id, Product product);

    void delete(Long id);

    Product findOne(Long id);

    List<Product> findAll(Pageable pageable, ProductCriteria criteria);

    List<Product> findBySeasonType(SeasonType seasonType, Pageable pageable);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    List<Product> findByBrandId(Long brandId, Pageable pageable);

    boolean isExistsById(Long id);
}

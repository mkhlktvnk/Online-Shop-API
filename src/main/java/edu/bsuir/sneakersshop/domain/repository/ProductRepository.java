package edu.bsuir.sneakersshop.domain.repository;

import edu.bsuir.sneakersshop.domain.entity.Product;
import edu.bsuir.sneakersshop.domain.enums.SeasonType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    List<Product> findAllBySeasonType(SeasonType seasonType);
    List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max);
    List<Product> findAllByBrandId(Long brandId);

}

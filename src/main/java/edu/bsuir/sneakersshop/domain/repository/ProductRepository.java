package edu.bsuir.sneakersshop.domain.repository;

import edu.bsuir.sneakersshop.domain.entity.Product;
import edu.bsuir.sneakersshop.domain.enums.SeasonType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>,
        PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllBySeasonType(SeasonType seasonType, Pageable pageable);
    List<Product> findAllByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);
    List<Product> findAllByBrandId(Long brandId, Pageable pageable);

}

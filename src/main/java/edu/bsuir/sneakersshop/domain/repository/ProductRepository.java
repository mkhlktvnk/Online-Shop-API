package edu.bsuir.sneakersshop.domain.repository;

import edu.bsuir.sneakersshop.domain.entity.Product;
import edu.bsuir.sneakersshop.domain.entity.enums.SeasonType;
import org.springframework.data.domain.Page;
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

    Page<Product> findAllByCategoriesId(Long categoriesId, Pageable pageable);

}

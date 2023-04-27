package edu.bsuir.onlineshop.domain.repository;

import edu.bsuir.onlineshop.domain.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {

    Page<Category> findAllByProductsId(Long productId, Pageable pageable);

    boolean existsByName(String name);

}

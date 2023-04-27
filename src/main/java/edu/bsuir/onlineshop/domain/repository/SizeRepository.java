package edu.bsuir.onlineshop.domain.repository;

import edu.bsuir.onlineshop.domain.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends CrudRepository<Size, Long> {
    Page<Size> findAllByProductId(Long productId, Pageable pageable);
}

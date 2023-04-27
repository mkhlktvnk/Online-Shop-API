package edu.bsuir.onlineshop.domain.repository;

import edu.bsuir.onlineshop.domain.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Page<Image> findAllByProductId(Long productId, Pageable pageable);
}

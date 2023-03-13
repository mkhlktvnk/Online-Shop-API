package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    Review findOne(Long id);

    List<Review> findAll(Pageable pageable);

    List<Review> findByUserId(Long userId, Pageable pageable);

    List<Review> findByProductId(Long productId, Pageable pageable);

    Review insert(Review review);

    void update(Long id, Review review);

    void delete(Long id);
}

package edu.bsuir.onlineshop.service;

import edu.bsuir.onlineshop.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    Review findById(Long id);

    Review findByProductAndReviewId(long productId, long reviewId);

    Page<Review> findAll(Pageable pageable);

    Page<Review> findAllByUserId(Long userId, Pageable pageable);

    Page<Review> findByProductId(Long productId, Pageable pageable);

    Review insert(Review review);

    Review makeReview(long userId, long productId, Review review);

    void update(Long id, Review review);

    void delete(Long id);

    void deleteByUserAndReviewId(long userId, long reviewId);
}

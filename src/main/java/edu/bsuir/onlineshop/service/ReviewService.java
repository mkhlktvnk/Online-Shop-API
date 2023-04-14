package edu.bsuir.onlineshop.service;

import edu.bsuir.onlineshop.domain.entity.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    Review findById(Long id);

    Review findByProductAndReviewId(long productId, long reviewId);

    List<Review> findAll(Pageable pageable);

    List<Review> findAllByUserId(Long userId, Pageable pageable);

    List<Review> findByProductId(Long productId, Pageable pageable);

    Review insert(Review review);

    Review makeReview(long userId, long productId, Review review);

    void update(Long id, Review review);

    void delete(Long id);

    void deleteByUserAndReviewId(long userId, long reviewId);
}

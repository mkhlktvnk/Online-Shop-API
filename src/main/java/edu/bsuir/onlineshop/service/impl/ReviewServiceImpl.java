package edu.bsuir.onlineshop.service.impl;

import edu.bsuir.onlineshop.domain.entity.Product;
import edu.bsuir.onlineshop.domain.entity.Review;
import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.domain.repository.ReviewRepository;
import edu.bsuir.onlineshop.service.ProductService;
import edu.bsuir.onlineshop.service.ReviewService;
import edu.bsuir.onlineshop.service.UserService;
import edu.bsuir.onlineshop.service.exception.ResourceNotFoundException;
import edu.bsuir.onlineshop.service.message.MessageKey;
import edu.bsuir.onlineshop.service.message.MessagesSource;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final UserService userService;
    private final MessagesSource messages;

    @Override
    @Cacheable(value = "review", key = "#id")
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage(MessageKey.REVIEW_NOT_FOUND_BY_ID, id)
                ));
    }

    @Override
    @Transactional
    public Review findByProductAndReviewId(long productId, long reviewId) {
        if (!productService.existsById(productId)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.PRODUCT_NOT_FOUND_BY_ID, productId)
            );
        }
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage(MessageKey.REVIEW_NOT_FOUND_BY_ID, reviewId)
                ));
    }

    @Override
    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Override
    public Page<Review> findAllByUserId(Long userId, Pageable pageable) {
        return reviewRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public Page<Review> findByProductId(Long productId, Pageable pageable) {
        if (!productService.existsById(productId)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.PRODUCT_NOT_FOUND_BY_ID, productId)
            );
        }
        return reviewRepository.findAllByProductId(productId, pageable);
    }

    @Override
    @CachePut(value = "review", key = "#result.id")
    public Review insert(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    @CachePut(value = "review", key = "#result.id")
    public Review makeReview(long userId, long productId, Review review) {
        User user = userService.findById(userId);
        Product product = productService.findById(productId);

        review.setProduct(product);
        review.setUser(user);

        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    @CachePut(value = "review", key = "#id")
    public void update(Long id, Review review) {
        Review reviewToUpdate = reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage(MessageKey.REVIEW_NOT_FOUND_BY_ID, id)
                ));
        reviewToUpdate.setId(id);
        reviewToUpdate.setTopic(review.getTopic());
        reviewToUpdate.setContent(review.getContent());
        reviewToUpdate.setMark(review.getMark());
        reviewRepository.save(reviewToUpdate);
    }

    @Override
    @Transactional
    @CacheEvict(value = "review", key = "#id")
    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.REVIEW_NOT_FOUND_BY_ID, id)
            );
        }
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "review", key = "#reviewId")
    public void deleteByUserAndReviewId(long userId, long reviewId) {
        if (!userService.existsById(userId)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.USER_NOT_FOUND_BY_ID, userId)
            );
        }
        if (!reviewRepository.existsById(reviewId)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.REVIEW_NOT_FOUND_BY_ID, reviewId)
            );
        }
        reviewRepository.deleteById(reviewId);
    }
}

package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Product;
import edu.bsuir.sneakersshop.domain.entity.Review;
import edu.bsuir.sneakersshop.domain.entity.User;
import edu.bsuir.sneakersshop.domain.repository.ReviewRepository;
import edu.bsuir.sneakersshop.service.ProductService;
import edu.bsuir.sneakersshop.service.ReviewService;
import edu.bsuir.sneakersshop.service.UserService;
import edu.bsuir.sneakersshop.service.exception.EntityNotFoundException;
import edu.bsuir.sneakersshop.service.message.MessagesSource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductService productService;
    private final UserService userService;
    private final MessagesSource messages;

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messages.getMessage("review.not-found")
                ));
    }

    @Override
    @Transactional
    public Review findByProductAndReviewId(long productId, long reviewId) {
        if (!productService.isExistsById(productId)) {
            throw new EntityNotFoundException(messages.getMessage("product.not-found"));
        }
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException(messages.getMessage("review.not-found")));
    }

    @Override
    public List<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Review> findByUserId(Long userId, Pageable pageable) {
        return reviewRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public List<Review> findByProductId(Long productId, Pageable pageable) {
        if (!productService.isExistsById(productId)) {
            throw new EntityNotFoundException(
                    messages.getMessage("review.not-found")
            );
        }
        return reviewRepository.findAllByProductId(productId, pageable);
    }

    @Override
    public Review insert(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public Review makeReview(long userId, long productId, Review review) {
        User user = userService.findById(userId);
        Product product = productService.findById(productId);

        review.setProduct(product);
        review.setUser(user);

        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public void update(Long id, Review review) {
        Review reviewToUpdate = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messages.getMessage("review.not-found")
                ));
        reviewToUpdate.setId(id);
        reviewToUpdate.setTopic(review.getTopic());
        reviewToUpdate.setContent(review.getContent());
        reviewToUpdate.setMark(review.getMark());
        reviewRepository.save(reviewToUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    messages.getMessage("review.not-found")
            );
        }
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteByUserAndReviewId(long userId, long reviewId) {
        if (!userService.existsById(userId)) {
            throw new EntityNotFoundException(messages.getMessage("user.not-found"));
        }
        if (!reviewRepository.existsById(reviewId)) {
            throw new EntityNotFoundException(messages.getMessage("review.not-found"));
        }
        reviewRepository.deleteById(reviewId);
    }
}

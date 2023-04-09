package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Review;
import edu.bsuir.sneakersshop.domain.repository.ReviewRepository;
import edu.bsuir.sneakersshop.service.ProductService;
import edu.bsuir.sneakersshop.service.ReviewService;
import edu.bsuir.sneakersshop.service.exception.EntityNotFoundException;
import edu.bsuir.sneakersshop.service.message.ProductMessages;
import edu.bsuir.sneakersshop.service.message.ReviewMessages;
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
    private final ReviewMessages reviewMessages;
    private final ProductMessages productMessages;

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(reviewMessages.getNotFoundMessage()));
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
            throw new EntityNotFoundException(productMessages.getNotFoundMessage());
        }
        return reviewRepository.findAllByProductId(productId, pageable);
    }

    @Override
    public Review insert(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public void update(Long id, Review review) {
        Review reviewToUpdate = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(reviewMessages.getNotFoundMessage()));
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
            throw new EntityNotFoundException(reviewMessages.getNotFoundMessage());
        }
        reviewRepository.deleteById(id);
    }
}

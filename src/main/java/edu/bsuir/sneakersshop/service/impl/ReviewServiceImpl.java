package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Review;
import edu.bsuir.sneakersshop.domain.repository.ReviewRepository;
import edu.bsuir.sneakersshop.service.ReviewService;
import edu.bsuir.sneakersshop.service.exception.EntityNotFoundException;
import edu.bsuir.sneakersshop.service.message.ReviewErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewErrorMessage reviewErrorMessage;

    @Override
    public Review findOne(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(reviewErrorMessage.getNotFoundMessage()));
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
        return reviewRepository.findAllByProductId(productId, pageable);
    }

    @Override
    public Review insert(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void update(Long id, Review review) {
        Review reviewToUpdate = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(reviewErrorMessage.getNotFoundMessage()));
        reviewToUpdate.setId(id);
        reviewToUpdate.setTopic(review.getTopic());
        reviewToUpdate.setContent(review.getContent());
        reviewToUpdate.setMark(review.getMark());
        reviewRepository.save(reviewToUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException(reviewErrorMessage.getNotFoundMessage());
        }
        reviewRepository.deleteById(id);
    }
}

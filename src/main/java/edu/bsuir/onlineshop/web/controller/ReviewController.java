package edu.bsuir.onlineshop.web.controller;

import edu.bsuir.onlineshop.domain.entity.Review;
import edu.bsuir.onlineshop.domain.entity.User;
import edu.bsuir.onlineshop.service.ReviewService;
import edu.bsuir.onlineshop.web.mapper.ReviewMapper;
import edu.bsuir.onlineshop.web.model.ReviewModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final PagedResourcesAssembler<Review> pagedResourcesAssembler;
    private final ReviewMapper mapper = Mappers.getMapper(ReviewMapper.class);

    @GetMapping("/products/{productId}/reviews")
    public PagedModel<ReviewModel> findAllByProductId(
            @PathVariable Long productId, @PageableDefault Pageable pageable) {
        Page<Review> reviews = reviewService.findByProductId(productId, pageable);
        return pagedResourcesAssembler.toModel(reviews, mapper::mapToModel);
    }

    @GetMapping("/users/{userId}/reviews")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public PagedModel<ReviewModel> findAllByUserId(
            @PathVariable Long userId, @PageableDefault Pageable pageable) {
        Page<Review> reviews = reviewService.findAllByUserId(userId, pageable);
        return pagedResourcesAssembler.toModel(reviews, mapper::mapToModel);
    }

    @GetMapping("/reviews/{id}")
    public ReviewModel findById(@PathVariable Long id) {
        return mapper.mapToModel(reviewService.findById(id));
    }

    @PostMapping("/products/{productId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewModel makeReview(
            @AuthenticationPrincipal User user, @PathVariable Long productId,
            @Valid @RequestBody ReviewModel reviewModel) {
        Review review = mapper.mapToEntity(reviewModel);
        return mapper.mapToModel(reviewService.makeReview(user.getId(), productId, review));
    }

    @PutMapping("/users/{userId}/reviews/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("#userId == authentication.principal.id")
    public void updateById(@PathVariable Long userId, @PathVariable Long reviewId,
                           @RequestBody ReviewModel reviewModel) {
        reviewService.update(reviewId, mapper.mapToEntity(reviewModel));
    }

    @DeleteMapping("/users/{userId}/reviews/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("#userId == authentication.principal.id")
    public void deleteById(@PathVariable Long userId, @PathVariable Long reviewId) {
        reviewService.delete(reviewId);
    }
}

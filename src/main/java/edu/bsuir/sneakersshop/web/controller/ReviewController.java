package edu.bsuir.sneakersshop.web.controller;

import edu.bsuir.sneakersshop.service.ReviewService;
import edu.bsuir.sneakersshop.web.mapper.ReviewMapper;
import edu.bsuir.sneakersshop.web.model.ReviewModel;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper mapper = Mappers.getMapper(ReviewMapper.class);

    @GetMapping("/products/{productId}/reviews")
    public List<ReviewModel> getReviewsByProductId(@PathVariable Long productId, @PageableDefault Pageable pageable) {
        return (List<ReviewModel>) mapper.mapToModel(reviewService.findByProductId(productId, pageable));
    }
}

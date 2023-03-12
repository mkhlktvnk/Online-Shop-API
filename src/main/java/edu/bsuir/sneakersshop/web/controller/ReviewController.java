package edu.bsuir.sneakersshop.web.controller;

import edu.bsuir.sneakersshop.service.ReviewService;
import edu.bsuir.sneakersshop.web.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
}

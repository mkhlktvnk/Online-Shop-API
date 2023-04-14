package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Review;
import edu.bsuir.onlineshop.web.model.ReviewModel;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface ReviewMapper {
    Review mapToEntity(ReviewModel reviewModel);

    ReviewModel mapToModel(Review review);

    Collection<Review> mapToEntity(Collection<ReviewModel> reviewModels);

    Collection<ReviewModel> mapToModel(Collection<Review> reviews);
}

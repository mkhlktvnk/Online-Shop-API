package edu.bsuir.sneakersshop.web.mapper;

import edu.bsuir.sneakersshop.domain.entity.Review;
import edu.bsuir.sneakersshop.web.model.ReviewModel;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface ReviewMapper {
    Review mapToEntity(ReviewModel reviewModel);

    ReviewModel mapToModel(Review review);

    Collection<Review> mapToEntity(Collection<ReviewModel> reviewModels);

    Collection<ReviewModel> mapToModel(Collection<Review> reviews);
}

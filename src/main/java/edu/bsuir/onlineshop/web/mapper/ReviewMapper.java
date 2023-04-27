package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Review;
import edu.bsuir.onlineshop.web.model.ReviewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {
        UserMapper.class,
        ProductMapper.class
})
public interface ReviewMapper {

    @Mappings({
            @Mapping(target = "userModel", source = "user"),
            @Mapping(target = "productModel", source = "product")
    })
    ReviewModel mapToModel(Review review);

    Review mapToEntity(ReviewModel reviewModel);

}

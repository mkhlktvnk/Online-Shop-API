package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Review;
import edu.bsuir.onlineshop.web.controller.ProductController;
import edu.bsuir.onlineshop.web.controller.ReviewController;
import edu.bsuir.onlineshop.web.controller.UserController;
import edu.bsuir.onlineshop.web.model.ReviewModel;
import edu.bsuir.onlineshop.web.model.UserModel;
import org.mapstruct.*;
import org.springframework.http.HttpMethod;

import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    Collection<Review> mapToEntity(Collection<ReviewModel> reviewModels);

    Collection<ReviewModel> mapToModel(Collection<Review> reviews);

}

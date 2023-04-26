package edu.bsuir.onlineshop.web.assembler;

import edu.bsuir.onlineshop.domain.entity.Review;
import edu.bsuir.onlineshop.web.link.handler.LinkHandler;
import edu.bsuir.onlineshop.web.mapper.ReviewMapper;
import edu.bsuir.onlineshop.web.model.ReviewModel;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewResourceAssembler implements RepresentationModelAssembler<Review, ReviewModel> {
    private final LinkHandler<ReviewModel> linkHandler;
    private final ReviewMapper mapper = Mappers.getMapper(ReviewMapper.class);

    @Override
    public ReviewModel toModel(Review entity) {
        ReviewModel model = mapper.mapToModel(entity);
        linkHandler.addLinks(model);
        return model;
    }
}

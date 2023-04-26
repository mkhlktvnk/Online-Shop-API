package edu.bsuir.onlineshop.web.assembler;

import edu.bsuir.onlineshop.domain.entity.Category;
import edu.bsuir.onlineshop.web.link.handler.LinkHandler;
import edu.bsuir.onlineshop.web.mapper.CategoryMapper;
import edu.bsuir.onlineshop.web.model.CategoryModel;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryResourceAssembler implements RepresentationModelAssembler<Category, CategoryModel> {
    private final LinkHandler<CategoryModel> linkHandler;
    private final CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @Override
    public CategoryModel toModel(Category entity) {
        CategoryModel model = mapper.mapToModel(entity);
        linkHandler.addLinks(model);
        return model;
    }
}

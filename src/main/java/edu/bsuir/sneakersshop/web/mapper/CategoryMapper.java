package edu.bsuir.sneakersshop.web.mapper;

import edu.bsuir.sneakersshop.domain.entity.Category;
import edu.bsuir.sneakersshop.web.model.CategoryModel;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryModel mapToModel(Category category);

    Category mapToEntity(CategoryModel categoryModel);
}

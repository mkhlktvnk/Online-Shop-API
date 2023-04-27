package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Category;
import edu.bsuir.onlineshop.web.model.CategoryModel;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryMapper {
    CategoryModel mapToModel(Category category);

    Category mapToEntity(CategoryModel categoryModel);
}

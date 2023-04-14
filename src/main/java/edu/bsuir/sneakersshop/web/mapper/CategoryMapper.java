package edu.bsuir.sneakersshop.web.mapper;

import edu.bsuir.sneakersshop.domain.entity.Category;
import edu.bsuir.sneakersshop.web.model.CategoryModel;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper
public interface CategoryMapper {
    CategoryModel mapToModel(Category category);

    Category mapToEntity(CategoryModel categoryModel);

    Page<CategoryModel> mapToModel(Page<Category> categories);
}

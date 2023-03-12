package edu.bsuir.sneakersshop.web.mapper;

import edu.bsuir.sneakersshop.domain.entity.Image;
import edu.bsuir.sneakersshop.web.model.ImageModel;
import org.mapstruct.Mapper;

@Mapper
public interface ImageMapper {
    Image mapToEntity(ImageModel imageModel);
    ImageModel mapToModel(Image image);
}

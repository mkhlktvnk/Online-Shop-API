package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Image;
import edu.bsuir.onlineshop.web.model.ImageModel;
import org.mapstruct.Mapper;

@Mapper
public interface ImageMapper {

    Image mapToEntity(ImageModel imageModel);
    ImageModel mapToModel(Image image);

}

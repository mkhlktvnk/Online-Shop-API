package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Image;
import edu.bsuir.onlineshop.web.model.ImageModel;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface ImageMapper {
    Image mapToEntity(ImageModel imageModel);
    ImageModel mapToModel(Image image);
    Collection<Image> mapToEntity(Collection<ImageModel> imageModels);
    Collection<ImageModel> mapToModel(Collection<Image> images);
}

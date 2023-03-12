package edu.bsuir.sneakersshop.web.mapper;

import edu.bsuir.sneakersshop.domain.entity.Size;
import edu.bsuir.sneakersshop.web.model.SizeModel;
import org.mapstruct.Mapper;

@Mapper
public interface SizeMapper {
    Size mapToEntity(SizeModel sizeModel);

    SizeModel mapToModel(Size size);
}

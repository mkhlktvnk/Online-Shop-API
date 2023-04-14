package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Size;
import edu.bsuir.onlineshop.web.model.SizeModel;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper
public interface SizeMapper {
    Size mapToEntity(SizeModel sizeModel);

    SizeModel mapToModel(Size size);

    Collection<Size> mapToEntity(Collection<SizeModel> sizeModels);

    Collection<SizeModel> mapToModel(Collection<Size> sizes);
}

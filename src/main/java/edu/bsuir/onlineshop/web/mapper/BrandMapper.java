package edu.bsuir.onlineshop.web.mapper;

import edu.bsuir.onlineshop.domain.entity.Brand;
import edu.bsuir.onlineshop.web.model.BrandModel;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface BrandMapper {
    Brand mapToEntity(BrandModel brandModel);

    BrandModel mapToModel(Brand brand);

    List<Brand> mapToEntity(Collection<BrandModel> brandModels);

    List<BrandModel> mapToModel(Collection<Brand> brands);
}

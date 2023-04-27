package edu.bsuir.onlineshop.web.assembler;

import edu.bsuir.onlineshop.domain.entity.Brand;
import edu.bsuir.onlineshop.web.link.handler.LinkHandler;
import edu.bsuir.onlineshop.web.mapper.BrandMapper;
import edu.bsuir.onlineshop.web.model.BrandModel;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandResourceAssembler implements RepresentationModelAssembler<Brand, BrandModel> {
    private final LinkHandler<BrandModel> linkHandler;
    private final BrandMapper mapper = Mappers.getMapper(BrandMapper.class);

    @Override
    public BrandModel toModel(Brand entity) {
        BrandModel model = mapper.mapToModel(entity);
        linkHandler.addLinks(model);
        return model;
    }

}

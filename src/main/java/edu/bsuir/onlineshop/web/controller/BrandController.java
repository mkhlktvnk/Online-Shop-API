package edu.bsuir.onlineshop.web.controller;

import edu.bsuir.onlineshop.domain.entity.Brand;
import edu.bsuir.onlineshop.service.BrandService;
import edu.bsuir.onlineshop.web.mapper.BrandMapper;
import edu.bsuir.onlineshop.web.model.BrandModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;
    private final PagedResourcesAssembler<Brand> pagedResourcesAssembler;
    private final BrandMapper mapper = Mappers.getMapper(BrandMapper.class);

    @GetMapping("/brands")
    public PagedModel<BrandModel> getBrands(@PageableDefault Pageable pageable) {
        Page<Brand> brands = brandService.findAll(pageable);
        return pagedResourcesAssembler.toModel(brands, mapper::mapToModel);
    }

    @GetMapping("/brands/{id}")
    public BrandModel getBrandById(@PathVariable Long id) {
        return mapper.mapToModel(brandService.findById(id));
    }

    @PostMapping("/brands")
    @ResponseStatus(HttpStatus.CREATED)
    public BrandModel insertBrand(@Valid @RequestBody BrandModel brand) {
        return mapper.mapToModel(
                brandService.insert(mapper.mapToEntity(brand))
        );
    }

    @PutMapping("/brands/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBrand(@PathVariable Long id, @Valid @RequestBody BrandModel brand) {
        brandService.update(id, mapper.mapToEntity(brand));
    }

    @DeleteMapping("/brand/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBrand(@PathVariable Long id) {
        brandService.delete(id);
    }
}

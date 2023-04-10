package edu.bsuir.sneakersshop.web.controller;

import edu.bsuir.sneakersshop.service.BrandService;
import edu.bsuir.sneakersshop.web.mapper.BrandMapper;
import edu.bsuir.sneakersshop.web.model.BrandModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;
    private final BrandMapper mapper = Mappers.getMapper(BrandMapper.class);

    @GetMapping("/brands")
    public List<BrandModel> getBrands(@PageableDefault Pageable pageable) {
        return mapper.mapToModel(brandService.findAll(pageable));
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

package edu.bsuir.onlineshop.web.controller;

import edu.bsuir.onlineshop.domain.entity.Size;
import edu.bsuir.onlineshop.service.SizeService;
import edu.bsuir.onlineshop.web.mapper.SizeMapper;
import edu.bsuir.onlineshop.web.model.SizeModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class SizeController {
    private final SizeService sizeService;
    private final PagedResourcesAssembler<Size> pagedResourcesAssembler;
    private final SizeMapper sizeMapper = Mappers.getMapper(SizeMapper.class);

    @GetMapping("/product/{productId}/sizes")
    public ResponseEntity<PagedModel<SizeModel>> findAllByProductId(
            @PathVariable Long productId, @PageableDefault Pageable pageable) {
        Page<Size> sizes = sizeService.findAllByProductId(productId, pageable);
        PagedModel<SizeModel> page = pagedResourcesAssembler.toModel(sizes, sizeMapper::mapToModel);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/product/{productId}/sizes")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SizeModel> addSizeToProduct(
            @PathVariable Long productId, @Valid @RequestBody SizeModel sizeModel) {
        Size size = sizeMapper.mapToEntity(sizeModel);
        SizeModel createdSize = sizeMapper.mapToModel(
                sizeService.addSizeToProduct(productId, size)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSize);
    }

    @DeleteMapping("/product/{productId}/sizes/{sizeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSizeById(@PathVariable Long productId, @PathVariable Long sizeId) {
        sizeService.deleteSize(productId, sizeId);
    }
}

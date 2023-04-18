package edu.bsuir.onlineshop.web.controller;

import edu.bsuir.onlineshop.domain.entity.Image;
import edu.bsuir.onlineshop.service.ImageService;
import edu.bsuir.onlineshop.web.mapper.ImageMapper;
import edu.bsuir.onlineshop.web.model.ImageModel;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final PagedResourcesAssembler<Image> pagedResourcesAssembler;
    private final ImageMapper mapper = Mappers.getMapper(ImageMapper.class);

    @GetMapping("/products/{productId}/images")
    public PagedModel<ImageModel> findAllByProductId(
            @PathVariable Long productId, @PageableDefault Pageable pageable) {
        Page<Image> images = imageService.findAllByProductId(productId, pageable);

        return pagedResourcesAssembler.toModel(images, mapper::mapToModel);
    }

    @PostMapping("/products/{productId}/images")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public ImageModel addImageToProduct(
            @PathVariable Long productId, @RequestBody ImageModel imageModel) {
        Image image = mapper.mapToEntity(imageModel);

        return mapper.mapToModel(
                imageService.addImageToProduct(productId, image)
        );
    }

    @PutMapping("/products/{productId}/images/{imageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateImage(@PathVariable Long productId, @PathVariable Long imageId,
                            @RequestBody ImageModel updateImage) {
        Image image = mapper.mapToEntity(updateImage);
        imageService.updateImage(productId, imageId, image);
    }

    @DeleteMapping("/products/{productId}/images/{imageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteImageById(@PathVariable Long productId, @PathVariable Long imageId) {
        imageService.deleteImage(productId, imageId);
    }
}

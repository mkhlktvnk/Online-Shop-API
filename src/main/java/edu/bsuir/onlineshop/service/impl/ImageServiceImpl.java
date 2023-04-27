package edu.bsuir.onlineshop.service.impl;

import edu.bsuir.onlineshop.domain.entity.Image;
import edu.bsuir.onlineshop.domain.entity.Product;
import edu.bsuir.onlineshop.domain.repository.ImageRepository;
import edu.bsuir.onlineshop.service.ImageService;
import edu.bsuir.onlineshop.service.ProductService;
import edu.bsuir.onlineshop.service.exception.ResourceNotFoundException;
import edu.bsuir.onlineshop.service.message.MessageKey;
import edu.bsuir.onlineshop.service.message.MessagesSource;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final ProductService productService;
    private final MessagesSource messages;

    @Override
    public Page<Image> findAllByProductId(long productId, Pageable pageable) {
        return imageRepository.findAllByProductId(productId, pageable);
    }

    @Override
    @Transactional
    @CachePut(value = "image", key = "#result.id")
    public Image addImageToProduct(long productId, Image image) {
        Product product = productService.findById(productId);

        image.setProduct(product);

        return imageRepository.save(image);
    }

    @Override
    @Transactional
    @CachePut(value = "image", key = "#imageId")
    public void updateImage(long productId, long imageId, Image updateImage) {
        if (!productService.existsById(productId)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.PRODUCT_NOT_FOUND_BY_ID, productId)
            );
        }

        Image image = imageRepository.findById(imageId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage(MessageKey.IMAGE_NOT_FOUND_BY_ID, imageId)
                ));

        image.setImageUrl(updateImage.getImageUrl());
        imageRepository.save(image);
    }

    @Override
    @Transactional
    @CacheEvict(value = "image", key = "#imageId")
    public void deleteImage(long productId, long imageId) {
        if (!productService.existsById(productId)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.PRODUCT_NOT_FOUND_BY_ID, productId)
            );
        }

        imageRepository.deleteById(imageId);
    }
}

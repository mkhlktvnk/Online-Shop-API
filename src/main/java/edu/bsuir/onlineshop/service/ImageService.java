package edu.bsuir.onlineshop.service;

import edu.bsuir.onlineshop.domain.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ImageService {
    Page<Image> findAllByProductId(long productId, Pageable pageable);

    Image addImageToProduct(long productId, Image image);

    void updateImage(long productId, long imageId, Image updateImage);

    void deleteImage(long productId, long imageId);
}

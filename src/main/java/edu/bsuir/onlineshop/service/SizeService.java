package edu.bsuir.onlineshop.service;

import edu.bsuir.onlineshop.domain.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SizeService {
    Page<Size> findAllByProductId(long productId, Pageable pageable);

    Size addSizeToProduct(long productId, Size size);

    void deleteSize(long productId, long sizeId);

}

package edu.bsuir.onlineshop.service.impl;

import edu.bsuir.onlineshop.domain.entity.Product;
import edu.bsuir.onlineshop.domain.entity.Size;
import edu.bsuir.onlineshop.domain.repository.ProductRepository;
import edu.bsuir.onlineshop.domain.repository.SizeRepository;
import edu.bsuir.onlineshop.service.ProductService;
import edu.bsuir.onlineshop.service.SizeService;
import edu.bsuir.onlineshop.service.exception.EntityNotFoundException;
import edu.bsuir.onlineshop.service.message.MessageKey;
import edu.bsuir.onlineshop.service.message.MessagesSource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    private final ProductService productService;
    private final MessagesSource messages;

    @Override
    public Page<Size> findAllByProductId(long productId, Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public Size addSizeToProduct(long productId, Size size) {
        Product product = productService.findById(productId);

        size.setProduct(product);

        return sizeRepository.save(size);
    }

    @Override
    @Transactional
    public void deleteSize(long productId, long sizeId) {
        if (!productService.existsById(productId)) {
            throw new EntityNotFoundException(
                    messages.getMessage(MessageKey.PRODUCT_NOT_FOUND_BY_ID, productId)
            );
        }
        if (!sizeRepository.existsById(sizeId)) {
            throw new EntityNotFoundException(
                    messages.getMessage(MessageKey.SIZE_NOT_FOUND_BY_ID, sizeId)
            );
        }
        sizeRepository.deleteById(sizeId);
    }
}

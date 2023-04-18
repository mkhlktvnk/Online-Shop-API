package edu.bsuir.onlineshop.service.impl;

import edu.bsuir.onlineshop.domain.entity.Product;
import edu.bsuir.onlineshop.domain.repository.CategoryRepository;
import edu.bsuir.onlineshop.domain.repository.ProductRepository;
import edu.bsuir.onlineshop.domain.spec.ProductSpecifications;
import edu.bsuir.onlineshop.service.ProductService;
import edu.bsuir.onlineshop.service.exception.ResourceNotFoundException;
import edu.bsuir.onlineshop.service.message.MessageKey;
import edu.bsuir.onlineshop.service.message.MessagesSource;
import edu.bsuir.onlineshop.web.criteria.ProductCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MessagesSource messages;

    @Override
    public Product insert(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void update(Long id, Product product) {
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage(MessageKey.PRODUCT_NOT_FOUND_BY_ID, id)
                ));
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setSeasonType(product.getSeasonType());
        productRepository.save(productToUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.PRODUCT_NOT_FOUND_BY_ID, id)
            );
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage(MessageKey.PRODUCT_NOT_FOUND_BY_ID, id)
                ));
    }

    @Override
    public Page<Product> findAll(Pageable pageable, ProductCriteria criteria) {
        Specification<Product> specification = Specification.allOf(
                ProductSpecifications.hasNameLike(criteria.getName()),
                ProductSpecifications.hasDescriptionLike(criteria.getDescription()),
                ProductSpecifications.hasBrandNameLike(criteria.getBrandName()),
                ProductSpecifications.hasPriceBetween(criteria.getMinPrice(), criteria.getMaxPrice())
        );
        return productRepository.findAll(specification, pageable);
    }

    @Override
    public Page<Product> findAllByCategoryId(long categoryId, Pageable pageable) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.CATEGORY_NOT_FOUND_BY_ID)
            );
        }
        return productRepository.findAllByCategoriesId(categoryId, pageable);
    }

    @Override
    public boolean existsById(Long id) {
        return productRepository.existsById(id);
    }
}

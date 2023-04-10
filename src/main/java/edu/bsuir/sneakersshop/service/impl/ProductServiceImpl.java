package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Product;
import edu.bsuir.sneakersshop.domain.repository.ProductRepository;
import edu.bsuir.sneakersshop.domain.spec.ProductSpecifications;
import edu.bsuir.sneakersshop.service.ProductService;
import edu.bsuir.sneakersshop.service.exception.EntityNotFoundException;
import edu.bsuir.sneakersshop.service.message.MessagesSource;
import edu.bsuir.sneakersshop.web.criteria.ProductCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final MessagesSource messages;

    @Override
    public Product insert(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void update(Long id, Product product) {
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messages.getMessage("product.not-found.message"
                )));
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setSeasonType(product.getSeasonType());
        productRepository.save(productToUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    messages.getMessage("product.not-found.message")
            );
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messages.getMessage(
                        "product.not-found.message"
                )));
    }

    @Override
    public List<Product> findAll(Pageable pageable, ProductCriteria criteria) {
        Specification<Product> specification = Specification.allOf(
                ProductSpecifications.hasNameLike(criteria.getName()),
                ProductSpecifications.hasDescriptionLike(criteria.getDescription()),
                ProductSpecifications.hasBrandNameLike(criteria.getBrandName()),
                ProductSpecifications.hasPriceBetween(criteria.getMinPrice(), criteria.getMaxPrice())
        );
        return productRepository.findAll(specification, pageable).getContent();
    }

    @Override
    public boolean isExistsById(Long id) {
        return productRepository.existsById(id);
    }
}

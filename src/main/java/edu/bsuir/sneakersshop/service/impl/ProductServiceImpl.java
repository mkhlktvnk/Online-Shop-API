package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Product;
import edu.bsuir.sneakersshop.domain.enums.SeasonType;
import edu.bsuir.sneakersshop.domain.repository.ProductRepository;
import edu.bsuir.sneakersshop.domain.spec.ProductSpecifications;
import edu.bsuir.sneakersshop.service.ProductService;
import edu.bsuir.sneakersshop.service.exception.EntityNotFoundException;
import edu.bsuir.sneakersshop.service.message.ProductMessages;
import edu.bsuir.sneakersshop.web.criteria.ProductCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMessages productMessages;

    @Override
    public Product insert(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void update(Long id, Product product) {
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(productMessages.getNotFoundMessage()));
        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setSeasonType(product.getSeasonType());
        productRepository.save(productToUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundException(productMessages.getNotFoundMessage());
        }
        productRepository.deleteById(id);
    }

    @Override
    public Product findOne(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(productMessages.getNotFoundMessage()));
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
    public List<Product> findBySeasonType(SeasonType seasonType, Pageable pageable) {
        return productRepository.findAllBySeasonType(seasonType, pageable);
    }

    @Override
    public List<Product> findByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable) {
        return productRepository.findAllByPriceBetween(min, max, pageable);
    }

    @Override
    public List<Product> findByBrandId(Long brandId, Pageable pageable) {
        return productRepository.findAllByBrandId(brandId, pageable);
    }

    @Override
    public boolean isExistsById(Long id) {
        return productRepository.existsById(id);
    }
}

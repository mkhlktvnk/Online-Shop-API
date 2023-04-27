package edu.bsuir.onlineshop.service.impl;

import edu.bsuir.onlineshop.domain.entity.Category;
import edu.bsuir.onlineshop.domain.repository.CategoryRepository;
import edu.bsuir.onlineshop.domain.repository.ProductRepository;
import edu.bsuir.onlineshop.service.CategoryService;
import edu.bsuir.onlineshop.service.exception.ResourceAlreadyPresentException;
import edu.bsuir.onlineshop.service.exception.ResourceNotFoundException;
import edu.bsuir.onlineshop.service.message.MessageKey;
import edu.bsuir.onlineshop.service.message.MessagesSource;
import edu.bsuir.onlineshop.web.criteria.CategoryCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static edu.bsuir.onlineshop.domain.spec.CategorySpecifications.hasDescriptionLike;
import static edu.bsuir.onlineshop.domain.spec.CategorySpecifications.hasNameLike;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final MessagesSource messagesSource;

    @Override
    public Page<Category> findAll(Pageable pageable, CategoryCriteria criteria) {
        Specification<Category> specification = Specification.where(hasNameLike(criteria.getName()))
                .and(hasDescriptionLike(criteria.getDescription()));

        return categoryRepository.findAll(specification, pageable);
    }

    @Override
    public Page<Category> findAllByProductId(long productId, Pageable pageable) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException(
                    messagesSource.getMessage(MessageKey.PRODUCT_NOT_FOUND_BY_ID, productId)
            );
        }

        return categoryRepository.findAllByProductsId(productId, pageable);
    }

    @Override
    @Cacheable(value = "category", key = "#id")
    public Category findById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messagesSource.getMessage(MessageKey.CATEGORY_NOT_FOUND_BY_ID, id)
                ));
    }

    @Override
    @Transactional
    @CachePut(value = "category", key = "#result.id")
    public Category insert(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new ResourceAlreadyPresentException(
                    messagesSource.getMessage(
                            MessageKey.CATEGORY_ALREADY_EXISTS_BY_NAME,
                            category.getName()
                    )
            );
        }

        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    @CachePut(value = "category", key = "#result.id")
    public void updateById(long id, Category updateCategory) {
        if (categoryRepository.existsByName(updateCategory.getName())) {
            throw new ResourceAlreadyPresentException(
                    messagesSource.getMessage(
                            MessageKey.CATEGORY_ALREADY_EXISTS_BY_NAME,
                            updateCategory.getName()
                    )
            );
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messagesSource.getMessage(MessageKey.CATEGORY_NOT_FOUND_BY_ID, id)
                ));
        category.setName(updateCategory.getName());
        category.setDescription(updateCategory.getDescription());
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    @CacheEvict(value = "category", key = "#result.id")
    public void deleteById(long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    messagesSource.getMessage(MessageKey.CATEGORY_NOT_FOUND_BY_ID, id)
            );
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return categoryRepository.existsById(id);
    }
}

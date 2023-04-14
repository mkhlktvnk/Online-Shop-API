package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Category;
import edu.bsuir.sneakersshop.domain.repository.CategoryRepository;
import edu.bsuir.sneakersshop.domain.spec.CategorySpecifications;
import edu.bsuir.sneakersshop.service.CategoryService;
import edu.bsuir.sneakersshop.service.ProductService;
import edu.bsuir.sneakersshop.service.exception.EntityAlreadyExistsException;
import edu.bsuir.sneakersshop.service.exception.EntityNotFoundException;
import edu.bsuir.sneakersshop.service.message.MessagesSource;
import edu.bsuir.sneakersshop.web.criteria.CategoryCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static edu.bsuir.sneakersshop.domain.spec.CategorySpecifications.hasDescriptionLike;
import static edu.bsuir.sneakersshop.domain.spec.CategorySpecifications.hasNameLike;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductService productService;
    private final MessagesSource messagesSource;

    @Override
    public Page<Category> findAll(Pageable pageable, CategoryCriteria criteria) {
        Specification<Category> specification = Specification.where(hasNameLike(criteria.getName()))
                .and(hasDescriptionLike(criteria.getDescription()));

        return categoryRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional
    public Page<Category> findAllByProductId(long productId, Pageable pageable) {
        if (!productService.isExistsById(productId)) {
            throw new EntityNotFoundException(
                    messagesSource.getMessage("product.not-found")
            );
        }
        return categoryRepository.findAllByProductsId(productId, pageable);
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messagesSource.getMessage("category.not-found")
                ));
    }

    @Override
    @Transactional
    public Category insert(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new EntityAlreadyExistsException(
                    messagesSource.getMessage("category.already-exists.by-name")
            );
        }
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void updateById(long id, Category updateCategory) {
        if (categoryRepository.existsByName(updateCategory.getName())) {
            throw new EntityAlreadyExistsException(
                    messagesSource.getMessage("category.already-exists.by-name")
            );
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messagesSource.getMessage("category.not-found")
                ));
        category.setName(updateCategory.getName());
        category.setDescription(updateCategory.getDescription());
        categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    messagesSource.getMessage("category.not-found")
            );
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long id) {
        return categoryRepository.existsById(id);
    }
}

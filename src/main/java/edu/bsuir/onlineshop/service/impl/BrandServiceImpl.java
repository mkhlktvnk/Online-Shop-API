package edu.bsuir.onlineshop.service.impl;

import edu.bsuir.onlineshop.domain.entity.Brand;
import edu.bsuir.onlineshop.domain.repository.BrandRepository;
import edu.bsuir.onlineshop.service.BrandService;
import edu.bsuir.onlineshop.service.exception.ResourceAlreadyPresentException;
import edu.bsuir.onlineshop.service.exception.ResourceNotFoundException;
import edu.bsuir.onlineshop.service.message.MessageKey;
import edu.bsuir.onlineshop.service.message.MessagesSource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final MessagesSource messages;

    @Override
    @Transactional
    public Brand insert(Brand brand) {
        if (brandRepository.existsByName(brand.getName())) {
            throw new ResourceAlreadyPresentException(
                    messages.getMessage(
                            MessageKey.BRAND_ALREADY_EXISTS_WITH_NAME,
                            brand.getName()
                    )
            );
        }
        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void update(Long id, Brand brand) {
        Brand brandToUpdate = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage(MessageKey.BRAND_NOT_FOUND_BY_ID, id)
                ));
        brandToUpdate.setName(brand.getName());
        brandToUpdate.setDescription(brand.getDescription());
        brandRepository.save(brandToUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    messages.getMessage(MessageKey.BRAND_NOT_FOUND_BY_ID, id)
            );
        }
        brandRepository.deleteById(id);
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messages.getMessage(MessageKey.BRAND_NOT_FOUND_BY_ID, id))
                );
    }

    @Override
    public Page<Brand> findAll(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }
}

package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Brand;
import edu.bsuir.sneakersshop.domain.repository.BrandRepository;
import edu.bsuir.sneakersshop.service.BrandService;
import edu.bsuir.sneakersshop.service.exception.EntityAlreadyExistsException;
import edu.bsuir.sneakersshop.service.exception.EntityNotFoundException;
import edu.bsuir.sneakersshop.service.message.MessagesSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final MessagesSource messages;

    @Override
    @Transactional
    public Brand insert(Brand brand) {
        if (brandRepository.existsByName(brand.getName())) {
            throw new EntityAlreadyExistsException(messages.getMessage("brand.not-found"));
        }
        return brandRepository.save(brand);
    }

    @Override
    @Transactional
    public void update(Long id, Brand brand) {
        Brand brandToUpdate = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messages.getMessage("brand.not-found")
                ));
        brandToUpdate.setName(brand.getName());
        brandToUpdate.setDescription(brand.getDescription());
        brandRepository.save(brandToUpdate);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new EntityNotFoundException(
                    messages.getMessage("brand.not-found")
            );
        }
        brandRepository.deleteById(id);
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        messages.getMessage("brand.not-found"))
                );
    }

    @Override
    public List<Brand> findAll(Pageable pageable) {
        return brandRepository.findAll(pageable).getContent();
    }
}

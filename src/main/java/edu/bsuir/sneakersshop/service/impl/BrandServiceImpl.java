package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Brand;
import edu.bsuir.sneakersshop.domain.repository.BrandRepository;
import edu.bsuir.sneakersshop.service.BrandService;
import edu.bsuir.sneakersshop.service.exception.EntityNotFoundException;
import edu.bsuir.sneakersshop.service.message.BrandErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final BrandErrorMessage brandErrorMessage;

    @Override
    public Brand insert(Brand brand) {
        return null;
    }

    @Override
    public void update(Long id, Brand brand) {
        Brand brandToUpdate = brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(brandErrorMessage.getNotFoundMessage()));
        brandToUpdate.setName(brand.getName());
        brandToUpdate.setDescription(brand.getDescription());
        brandRepository.save(brandToUpdate);
    }

    @Override
    public void delete(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new EntityNotFoundException(brandErrorMessage.getNotFoundMessage());
        }
        brandRepository.deleteById(id);
    }

    @Override
    public Brand findOne(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(brandErrorMessage.getNotFoundMessage()));
    }

    @Override
    public Brand findOneByName(String name) {
        return brandRepository.findByName(brandErrorMessage.getNotFoundMessage())
                .orElseThrow(() -> new EntityNotFoundException(brandErrorMessage.getNotFoundMessage()));
    }

    @Override
    public List<Brand> findAll(Pageable pageable) {
        return null;
    }
}

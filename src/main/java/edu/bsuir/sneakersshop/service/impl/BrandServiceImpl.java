package edu.bsuir.sneakersshop.service.impl;

import edu.bsuir.sneakersshop.domain.entity.Brand;
import edu.bsuir.sneakersshop.domain.repository.BrandRepository;
import edu.bsuir.sneakersshop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public Brand insert(Brand brand) {
        return null;
    }

    @Override
    public void update(Long id, Brand brand) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Brand findOne(Long id) {
        return null;
    }

    @Override
    public Brand findOneByName(String name) {
        return null;
    }

    @Override
    public List<Brand> findAll(Pageable pageable) {
        return null;
    }
}

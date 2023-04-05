package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.Brand;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BrandService {
    Brand insert(Brand brand);

    void update(Long id, Brand brand);

    void delete(Long id);

    Brand findById(Long id);

    List<Brand> findAll(Pageable pageable);
}

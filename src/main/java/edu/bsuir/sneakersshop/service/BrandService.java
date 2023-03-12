package edu.bsuir.sneakersshop.service;

import edu.bsuir.sneakersshop.domain.entity.Brand;

import java.awt.print.Pageable;
import java.util.List;

public interface BrandService {
    Brand insert(Brand brand);

    void update(Long id, Brand brand);

    void delete(Long id);

    Brand findOne(Long id);

    Brand findOneByName(String name);

    List<Brand> findAll(Pageable pageable);
}

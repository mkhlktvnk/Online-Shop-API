package edu.bsuir.sneakersshop.domain.repository;

import edu.bsuir.sneakersshop.domain.entity.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long>, PagingAndSortingRepository<Brand, Long> {

    Optional<Brand> findByName(String name);
    boolean existsByName(String name);

}

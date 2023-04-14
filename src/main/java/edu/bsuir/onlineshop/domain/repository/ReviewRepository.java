package edu.bsuir.onlineshop.domain.repository;

import edu.bsuir.onlineshop.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long>, PagingAndSortingRepository<Review, Long> {

    Page<Review> findAllByUserId(Long id, Pageable pageable);
    
    Page<Review> findAllByProductId(Long id, Pageable pageable);

}

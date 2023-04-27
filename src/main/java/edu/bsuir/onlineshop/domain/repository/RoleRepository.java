package edu.bsuir.onlineshop.domain.repository;

import edu.bsuir.onlineshop.domain.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByAuthority(String authority);

}

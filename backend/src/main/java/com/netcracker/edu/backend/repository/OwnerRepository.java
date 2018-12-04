package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Owner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByUserId(Long id);
    Iterable<Owner> findAll(Pageable pageable);

}

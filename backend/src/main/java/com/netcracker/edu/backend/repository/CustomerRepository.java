package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByUserId(Long id);
    Iterable<Customer> findAll(Pageable pageable);

}

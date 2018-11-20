package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
}

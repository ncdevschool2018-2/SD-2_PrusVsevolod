package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Subscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    Iterable<Subscription> findAll(Pageable pageable);
    Iterable<Subscription> findByOwnerId(Long id);
}

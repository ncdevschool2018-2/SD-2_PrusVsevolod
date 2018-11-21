package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.ActiveSubscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveSubscriptionRepository extends CrudRepository<ActiveSubscription, Long> {

    Iterable<ActiveSubscription> findByCustomerId(Long id);
}

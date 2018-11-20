package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.CustomerToSubscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveSubscriptionRepository extends CrudRepository<CustomerToSubscription, Long> {
}

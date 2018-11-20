package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.CustomerToSubscription;

import java.util.Optional;

public interface ActiveSubscriptionService {

    Optional<CustomerToSubscription> getActiveSubscriptionById(Long id);
    Iterable<CustomerToSubscription> getAllActiveSubscriptions();
}

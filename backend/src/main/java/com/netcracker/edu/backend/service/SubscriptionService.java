package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Subscription;

import java.util.Optional;

public interface SubscriptionService {

    Iterable<Subscription> findAll(int page, int size);
    Optional<Subscription> getSubscriptionById(Long id);
    Iterable<Subscription> getAllSubscriptions();
    Iterable<Subscription> getByOwnerId(Long id);
    Subscription saveSubscription(Subscription subscription);
    void deleteSubscription(Long id);
}

package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Subscription;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public interface SubscriptionService {
    Iterable<Subscription> findAllWithSorting(int page, int size, Sort sort);
    Optional<Subscription> getSubscriptionById(Long id);
    Iterable<Subscription> findByNameContaining(String name, int page, int size);
    Iterable<Subscription> getByOwnerId(Long id);
    Subscription saveSubscription(Subscription subscription);
    void deleteSubscription(Long id);
}

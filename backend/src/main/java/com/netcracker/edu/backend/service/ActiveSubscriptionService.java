package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.ActiveSubscription;

import java.util.List;
import java.util.Optional;

public interface ActiveSubscriptionService {

    Optional<ActiveSubscription> getActiveSubscriptionById(Long id);
    Iterable<ActiveSubscription> getAllActiveSubscriptionsByCustomerId(Long id);
    Iterable<ActiveSubscription> getAllActiveSubscriptions();
    Iterable<ActiveSubscription> saveActiveSubscriptions(List<ActiveSubscription> activeSubscription);
    ActiveSubscription saveActiveSubscription(ActiveSubscription activeSubscription);
    void deleteActiveSubscriptionById (Long id);
}

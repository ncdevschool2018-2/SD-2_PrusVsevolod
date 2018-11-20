package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.CustomerToSubscription;
import com.netcracker.edu.backend.repository.ActiveSubscriptionRepository;
import com.netcracker.edu.backend.service.ActiveSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ActiveSubscriptionServiceImpl implements ActiveSubscriptionService {

    private ActiveSubscriptionRepository repository;

    @Autowired
    public ActiveSubscriptionServiceImpl(ActiveSubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<CustomerToSubscription> getActiveSubscriptionById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<CustomerToSubscription> getAllActiveSubscriptions() {
        return repository.findAll();
    }
}

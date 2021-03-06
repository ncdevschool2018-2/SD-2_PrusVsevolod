package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.repository.SubscriptionRepository;
import com.netcracker.edu.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository repository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<Subscription> findAllWithSorting(int page, int size, Sort sort) {
        return repository.findAll(PageRequest.of(page,size, sort));
    }

    @Override
    public Optional<Subscription> getSubscriptionById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Subscription> findByNameContaining(String name, int page, int size) {
        return repository.findByNameContaining(name, PageRequest.of(page,size));
    }


    @Override
    public Iterable<Subscription> getByOwnerId(Long id) {
        return repository.findByOwnerId(id);
    }

    @Override
    public Iterable<Subscription> getByCategoryId(Long id, int page, int size) {
        return repository.findByCategoryId(id, PageRequest.of(page, size));
    }

    @Override
    public Subscription saveSubscription(Subscription subscription) {
        return repository.save(subscription);
    }

    @Override
    public void deleteSubscription(Long id) {
        repository.deleteById(id);
    }
}

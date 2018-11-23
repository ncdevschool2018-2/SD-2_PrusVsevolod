package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.ActiveSubscription;
import com.netcracker.edu.backend.repository.ActiveSubscriptionRepository;
import com.netcracker.edu.backend.service.ActiveSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ActiveSubscriptionServiceImpl implements ActiveSubscriptionService {

    private ActiveSubscriptionRepository repository;

    @Autowired
    public ActiveSubscriptionServiceImpl(ActiveSubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ActiveSubscription> getActiveSubscriptionById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<ActiveSubscription> getAllActiveSubscriptionsByCustomerId(Long id) {
        return repository.findByCustomerId(id);
    }

    @Override
    public Iterable<ActiveSubscription> getAllActiveSubscriptions() {
        return repository.findAll();
    }

    @Override
    public Iterable<ActiveSubscription> saveActiveSubscriptions(List<ActiveSubscription> activeSubscription) {
        List<ActiveSubscription> sameItems = new ArrayList<>();

        List<ActiveSubscription> currentAS = (List<ActiveSubscription>)repository.findByCustomerId(activeSubscription.get(0).getCustomerId());
        for(ActiveSubscription currentASItem: currentAS){
            for(ActiveSubscription activeSubscriptionItem: activeSubscription){
                if(currentASItem.getCustomerId() == activeSubscriptionItem.getCustomerId() && currentASItem.getSubscription().getId() == activeSubscriptionItem.getSubscription().getId()){
                    currentASItem.setQuantity(currentASItem.getQuantity()+activeSubscriptionItem.getQuantity());
                    repository.save(currentASItem);

                    sameItems.add(activeSubscriptionItem);
                }
            }
        }
        activeSubscription.removeAll(sameItems);

        return repository.saveAll(activeSubscription);
    }

    @Override
    public ActiveSubscription saveActiveSubscription(ActiveSubscription activeSubscription) {
        return repository.save(activeSubscription);
    }

    @Override
    public void deleteActiveSubscriptionById(Long id) {
        repository.deleteById(id);
    }


}

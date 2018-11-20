package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.CustomerToSubscription;
import com.netcracker.edu.backend.service.ActiveSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/Active_subscriptions")
public class ActiveSubscriptionController {

    private ActiveSubscriptionService activeSubscriptionService;

    @Autowired
    public ActiveSubscriptionController(ActiveSubscriptionService activeSubscriptionService) {
        this.activeSubscriptionService = activeSubscriptionService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerToSubscription> getActiveSubscriptionById(@PathVariable(name = "id") Long id) {
        Optional<CustomerToSubscription> activeSubscription = activeSubscriptionService.getActiveSubscriptionById(id);
        if (activeSubscription.isPresent()) {
            return ResponseEntity.ok(activeSubscription.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<CustomerToSubscription> getAllActiveSubscriptions() {
        return activeSubscriptionService.getAllActiveSubscriptions();
    }

}

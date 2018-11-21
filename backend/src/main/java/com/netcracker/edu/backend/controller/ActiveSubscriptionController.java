package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.ActiveSubscription;
import com.netcracker.edu.backend.service.ActiveSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/active_subscription")
public class ActiveSubscriptionController {

    private ActiveSubscriptionService activeSubscriptionService;

    @Autowired
    public ActiveSubscriptionController(ActiveSubscriptionService activeSubscriptionService) {
        this.activeSubscriptionService = activeSubscriptionService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ActiveSubscription> getActiveSubscriptionById(@PathVariable(name = "id") Long id) {
        Optional<ActiveSubscription> activeSubscription = activeSubscriptionService.getActiveSubscriptionById(id);
        if (activeSubscription.isPresent()) {
            return ResponseEntity.ok(activeSubscription.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ActiveSubscription>> getAllActiveSubscriptionsByCustomerId(@PathVariable(name = "id") Long id) {
        Iterable<ActiveSubscription> activeSubscriptions = activeSubscriptionService.getAllActiveSubscriptionsByCustomerId(id);
        if (activeSubscriptions != null) {
            return ResponseEntity.ok(activeSubscriptions);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<ActiveSubscription> getAllActiveSubscriptions() {
        return activeSubscriptionService.getAllActiveSubscriptions();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Iterable<ActiveSubscription> saveActiveSubscription(@RequestBody List<ActiveSubscription> activeSubscription) {
//        System.out.println("Qweqwe");
//        System.out.println(activeSubscription.get(0).getActivationDate());
//        System.out.println(activeSubscription.size());
        return activeSubscriptionService.saveActiveSubscriptions(activeSubscription);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteActiveSubscription(@PathVariable(name = "id") Long id) {
        activeSubscriptionService.deleteActiveSubscriptionById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService){
        this.subscriptionService = subscriptionService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable(name = "id") Long id) {
        Optional<Subscription> subscriptionById = subscriptionService.getSubscriptionById(id);
        if (subscriptionById.isPresent()) {
            return ResponseEntity.ok(subscriptionById.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public Iterable<Subscription> getAllSubscriptions() {
//        return subscriptionService.getAllSubscriptions();
//    }


    @RequestMapping(params = { "page", "size" },method = RequestMethod.GET)
    public Iterable<Subscription> findSubscriptions(@RequestParam( "page" ) int page, @RequestParam( "size" ) int size) {
        return subscriptionService.findAll(page,size);
    }

    @RequestMapping(value = "/owner/{ownerId}", method = RequestMethod.GET)
    public Iterable<Subscription> findByOwnerId(@PathVariable(name = "ownerId") Long id) {
        return subscriptionService.getByOwnerId(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Subscription saveSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.saveSubscription(subscription);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Subscription saveEditedSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.saveSubscription(subscription);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSubscription(@PathVariable(name = "id") Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseEntity.noContent().build();
    }
}

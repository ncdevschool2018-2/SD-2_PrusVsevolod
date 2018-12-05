package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.model.Content;
import com.netcracker.edu.fapi.model.OwnerViewModel;
import com.netcracker.edu.fapi.model.SubscriptionViewModel;
import com.netcracker.edu.fapi.service.OwnerDataService;
import com.netcracker.edu.fapi.service.SubscriptionDataService;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionDataController {

    @Autowired
    private SubscriptionDataService subscriptionDataService;
    @Autowired
    private OwnerDataService ownerDataService;
    @Autowired
    private UserDataService userDataService;

    @RequestMapping(params = {"page", "size"})
    public ResponseEntity<Content> findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(subscriptionDataService.findAll(page, size));
    }

    @RequestMapping(value = "/category/{id}", params = {"page", "size"})
    public ResponseEntity<Content> findByCategoryId(@PathVariable("id") Long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(subscriptionDataService.findByCategoryId(id, page, size));
    }

    @RequestMapping(value = "/search", params = {"name", "page", "size"})
    public ResponseEntity<Content> findByNameLike(@RequestParam("name") String name, @RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(subscriptionDataService.findByNameLike(name, page, size));
    }

    @RequestMapping(value = "/owner/{ownerId}")
    public ResponseEntity<List<SubscriptionViewModel>> getSubscriptionsByOwnerId(@PathVariable(name = "ownerId") String id) {
        return ResponseEntity.ok(subscriptionDataService.findByOwnerId(Long.valueOf(id)));
    }

    @PreAuthorize("hasAnyAuthority('owner')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SubscriptionViewModel> saveSubscription(@RequestBody SubscriptionViewModel subscription) {
        OwnerViewModel owner = ownerDataService.getOwnerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        if (owner.getBa() == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(subscriptionDataService.saveSubscription(subscription));
    }

//    @PreAuthorize("hasAnyAuthority('owner')")
//    @RequestMapping(method = RequestMethod.PUT)
//    public ResponseEntity<SubscriptionViewModel> saveEditedSubscription(@RequestBody SubscriptionViewModel subscription) {
//        return ResponseEntity.ok(subscriptionDataService.saveSubscription(subscription));
//    }

    @PreAuthorize("hasAnyAuthority('owner')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable String id) {
        subscriptionDataService.deleteSubscription(Long.valueOf(id));
    }

}

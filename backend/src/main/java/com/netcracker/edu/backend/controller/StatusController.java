package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Status;
import com.netcracker.edu.backend.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/subscription_statuses")
public class StatusController {

    private StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Status> getSubscriptionStatusById(@PathVariable(name = "id") Long id) {
        Optional<Status> subscriptionStatusById = statusService.getStatusById(id);
        if (subscriptionStatusById.isPresent()) {
            return ResponseEntity.ok(subscriptionStatusById.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Status> getAllSubscriptionStatuses() {
        return statusService.getAllStatuses();
    }
}

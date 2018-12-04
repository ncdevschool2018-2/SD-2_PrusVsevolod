package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Owner;
import com.netcracker.edu.backend.service.BillingAccountService;
import com.netcracker.edu.backend.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private OwnerService ownerService;
    private BillingAccountService billingAccountService;

    @Autowired
    public OwnerController(OwnerService ownerService, BillingAccountService billingAccountService){
        this.ownerService = ownerService;
        this.billingAccountService = billingAccountService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Owner> getOwnerById(@PathVariable(name = "id") Long id) {
        Optional<Owner> ownerById = ownerService.getOwnerById(id);
        if (ownerById.isPresent()) {
            return ResponseEntity.ok(ownerById.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(params = {"page", "size"})
    public Iterable<Owner> getAllOwners(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ownerService.getAllOwners(page, size);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Owner saveOwner(@RequestBody Owner owner){
        return ownerService.saveOwner(owner);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Owner saveEditedOwner(@RequestBody Owner owner){
        return ownerService.saveOwner(owner);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOwner(@PathVariable(name = "id") Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Owner> getOwnerByUserId(@PathVariable(name = "id") Long id) {
        Owner owner = ownerService.findByUserId(id);
        if (owner != null) {
            return ResponseEntity.ok(owner);
        }
        else {
            return ResponseEntity.noContent().build();
        }

    }

    @RequestMapping(value = "/ba", method = RequestMethod.POST)
    public Owner saveBa(@RequestBody Owner owner){
        billingAccountService.saveBa(owner.getba());
        return ownerService.saveOwner(owner);

    }
}

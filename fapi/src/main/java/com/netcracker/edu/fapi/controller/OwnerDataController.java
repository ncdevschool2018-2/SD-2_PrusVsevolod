package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.model.BaViewModel;
import com.netcracker.edu.fapi.model.Content;
import com.netcracker.edu.fapi.model.OwnerViewModel;
import com.netcracker.edu.fapi.service.OwnerDataService;
import com.netcracker.edu.fapi.service.UserDataService;
import com.netcracker.edu.fapi.transfer.Exist;
import com.netcracker.edu.fapi.transfer.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/owners")
public class OwnerDataController {

    @Autowired
    private OwnerDataService ownerDataService;
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(params = {"page", "size"})
    public ResponseEntity<Content> getAllOwners(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(ownerDataService.getAll(page, size));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OwnerViewModel> saveOwner(@Validated(New.class) @RequestBody OwnerViewModel owner) {
        owner.getUser().setPassword(passwordEncoder.encode(owner.getUser().getPassword()));
        return ResponseEntity.ok(ownerDataService.saveOwner(owner));
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<OwnerViewModel> saveEditedOwner(@Validated(Exist.class) @RequestBody OwnerViewModel owner) {
        ownerDataService.saveEditedOwner(owner);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OwnerViewModel> getOwnerById(@PathVariable(name = "id") Long id) {
        Optional<OwnerViewModel> owner = ownerDataService.getOwnerById(id);
        if (owner.isPresent()) {
            return ResponseEntity.ok(owner.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('admin', 'owner')")
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<OwnerViewModel> getOwnerByUserId() {
        OwnerViewModel owner = ownerDataService.getOwnerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        if (owner != null) {
            return ResponseEntity.ok(owner);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteOwner(@PathVariable String id) {
        ownerDataService.deleteOwner(Long.valueOf(id));
    }

    @PreAuthorize("hasAnyAuthority('owner')")
    @RequestMapping(value = "/ba", method = RequestMethod.POST)
    public ResponseEntity<BaViewModel> saveBa(@Validated(New.class) @RequestBody BaViewModel ba) {
        OwnerViewModel owner = ownerDataService.getOwnerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        if (owner.getBa() == null && ba.getNumber() != null) {
            owner.setBa(ba);
            return ResponseEntity.ok(ownerDataService.saveOwnerBa(owner).getBa());
        }
        return ResponseEntity.notFound().build();
    }
}

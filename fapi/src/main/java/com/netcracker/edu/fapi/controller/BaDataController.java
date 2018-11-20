package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.model.BaViewModel;
import com.netcracker.edu.fapi.service.BaDataService;
import com.netcracker.edu.fapi.transfer.Exist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/billing_accounts")
public class BaDataController {

    @Autowired
    private BaDataService baDataService;

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping
    public ResponseEntity<List<BaViewModel>> getAllBa() {
        return ResponseEntity.ok(baDataService.getAll());
    }

    @PreAuthorize("hasAnyAuthority('owner', 'customer')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<BaViewModel> saveEditedBa(@Validated(Exist.class) @RequestBody BaViewModel ba) {
        baDataService.saveEditedBa(ba);
        return ResponseEntity.ok().build();
    }
}

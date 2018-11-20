package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.BillingAccount;
import com.netcracker.edu.backend.service.BillingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/billing_accounts")
public class BillingAccountController {

    private BillingAccountService billingAccountService;

    @Autowired
    public BillingAccountController(BillingAccountService billingAccountService){
        this.billingAccountService = billingAccountService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BillingAccount> getBillingAccountById(@PathVariable(name = "id") Long id) {
        Optional<BillingAccount> billing_accountsOptional = billingAccountService.getBillingAccountById(id);
        if (billing_accountsOptional.isPresent()) {
            return ResponseEntity.ok(billing_accountsOptional.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<BillingAccount> getAllBillingAccounts() {
        return billingAccountService.getAllBillingAccounts();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public BillingAccount saveBillingAccount(@RequestBody BillingAccount ba) {
        return billingAccountService.addAmountOnBa(ba);
    }
}

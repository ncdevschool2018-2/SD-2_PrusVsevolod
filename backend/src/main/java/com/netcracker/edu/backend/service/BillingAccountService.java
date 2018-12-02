package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.BillingAccount;

import java.util.Optional;

public interface BillingAccountService {

    BillingAccount saveBa(BillingAccount ba);
    void deleteBa(BillingAccount ba);
    Optional<BillingAccount> getBillingAccountById(Long id);
    Iterable<BillingAccount> getAllBillingAccounts();
    BillingAccount addAmountOnBa(BillingAccount ba);
}

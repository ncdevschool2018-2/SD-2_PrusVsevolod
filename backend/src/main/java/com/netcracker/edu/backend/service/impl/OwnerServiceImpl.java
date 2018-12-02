package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Owner;
import com.netcracker.edu.backend.repository.OwnerRepository;
import com.netcracker.edu.backend.service.BillingAccountService;
import com.netcracker.edu.backend.service.OwnerService;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class OwnerServiceImpl implements OwnerService {

    private UserService userService;
    private OwnerRepository ownerRepository;
    private BillingAccountService billingAccountService;

    @Autowired
    public OwnerServiceImpl(UserService userService, OwnerRepository repository, BillingAccountService billingAccountService) {
        this.userService = userService;
        this.ownerRepository = repository;
        this.billingAccountService = billingAccountService;
    }


    @Override
    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Iterable<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Transactional
    @Override
    public Owner saveOwner(Owner owner) {
        userService.saveUser(owner.getuser());
        return ownerRepository.save(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        Owner deletedOwner = getOwnerById(id).get();
        Long UserId = deletedOwner.getuser().getId();
        if (deletedOwner.getba() != null) {
            billingAccountService.deleteBa(deletedOwner.getba());
        }
        userService.deleteUser(UserId);
    }

    @Override
    public Owner findByUserId(Long id) {
        return ownerRepository.findByUserId(id);
    }
}

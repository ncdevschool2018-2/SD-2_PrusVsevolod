package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Owner;

import java.util.Optional;

public interface OwnerService {

    Optional<Owner> getOwnerById(Long id);
    Iterable<Owner> getAllOwners(int page, int size);
    Owner saveOwner(Owner owner);
    void deleteOwner(Long id);
    Owner findByUserId(Long id);

}

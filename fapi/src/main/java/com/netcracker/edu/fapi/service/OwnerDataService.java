package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.OwnerViewModel;

import java.util.List;
import java.util.Optional;

public interface OwnerDataService {
    List<OwnerViewModel> getAll();
    Optional<OwnerViewModel> getOwnerById(Long id);
    OwnerViewModel getOwnerByUserId(Long id);
    OwnerViewModel saveOwner(OwnerViewModel owner);
    void saveEditedOwner(OwnerViewModel owner);
    OwnerViewModel saveOwnerBa(OwnerViewModel owner);
    void deleteOwner(Long id);
}

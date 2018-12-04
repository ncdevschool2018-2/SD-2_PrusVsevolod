package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.Content;
import com.netcracker.edu.fapi.model.OwnerViewModel;

import java.util.Optional;

public interface OwnerDataService {
    Content<OwnerViewModel> getAll(int page, int size);
    Optional<OwnerViewModel> getOwnerById(Long id);
    OwnerViewModel getOwnerByUserId(Long id);
    OwnerViewModel saveOwner(OwnerViewModel owner);
    void saveEditedOwner(OwnerViewModel owner);
    OwnerViewModel saveOwnerBa(OwnerViewModel owner);
    void deleteOwner(Long id);
}

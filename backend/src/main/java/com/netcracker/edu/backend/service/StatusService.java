package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Status;

import java.util.Optional;

public interface StatusService {

    Optional<Status> getStatusById(Long id);
    Iterable<Status> getAllStatuses();
}

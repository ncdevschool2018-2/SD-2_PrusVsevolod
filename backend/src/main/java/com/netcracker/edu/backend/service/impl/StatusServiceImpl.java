package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Status;
import com.netcracker.edu.backend.repository.StatusRepository;
import com.netcracker.edu.backend.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StatusServiceImpl implements StatusService {

    private StatusRepository repository;

    @Autowired
    public StatusServiceImpl(StatusRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<Status> getStatusById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Status> getAllStatuses() {
        return repository.findAll();
    }
}

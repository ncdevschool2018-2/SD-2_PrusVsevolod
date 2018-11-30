package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Owner;
import com.netcracker.edu.backend.repository.OwnerRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class OwnerServiceImpl implements OwnerService {

    private UserRepository userRepository;
    private OwnerRepository ownerRepository;

    @Autowired
    public OwnerServiceImpl(UserRepository userRepository, OwnerRepository repository) {
        this.userRepository = userRepository;
        this.ownerRepository = repository;
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
        userRepository.save(owner.getuser());
        return ownerRepository.save(owner);
    }

    @Override
    public void deleteOwner(Long id) {
        Long UserId = getOwnerById(id).get().getuser().getId();
//        ownersRepository.deleteById(id);
        userRepository.deleteById(UserId);
    }

    @Override
    public Owner findByUserId(Long id) {
        return ownerRepository.findByUserId(id);
    }
}

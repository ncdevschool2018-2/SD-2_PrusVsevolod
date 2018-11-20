package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Role;

import java.util.Optional;

public interface RoleService {

    Optional<Role> getRoleById(Long id);
    Iterable<Role> getAllRoles();
}

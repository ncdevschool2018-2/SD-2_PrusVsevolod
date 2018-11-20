package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Role;
import com.netcracker.edu.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Role> getRoleById(@PathVariable(name = "id") Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            return ResponseEntity.ok(role.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

}

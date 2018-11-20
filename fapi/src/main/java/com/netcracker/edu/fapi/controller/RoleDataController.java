package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.model.RoleViewModel;
import com.netcracker.edu.fapi.service.RoleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('admin')")
@RequestMapping("/api/roles")
public class RoleDataController {

    @Autowired
    private RoleDataService roleDataService;

    @RequestMapping
    public ResponseEntity<List<RoleViewModel>> getAllRoles() {
        return ResponseEntity.ok(roleDataService.getAll());
    }

}

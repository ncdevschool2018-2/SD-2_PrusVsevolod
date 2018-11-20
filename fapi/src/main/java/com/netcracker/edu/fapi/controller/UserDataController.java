package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.model.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping
    public ResponseEntity<List<UserViewModel>> getAllUsers() {
        return ResponseEntity.ok(userDataService.getAll());
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserViewModel> getUserById(@PathVariable(name = "id") Long id){
        Optional<UserViewModel> user = userDataService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('admin', 'owner', 'customer')")//Берется из контекста
    @RequestMapping(value = "userLogin/", method = RequestMethod.GET)
    public ResponseEntity<UserViewModel> getUser() {
        UserViewModel currentUser = userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        if (currentUser != null) {
            return ResponseEntity.ok(currentUser);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
}

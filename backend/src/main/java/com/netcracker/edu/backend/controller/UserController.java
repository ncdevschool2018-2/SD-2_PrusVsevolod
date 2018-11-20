package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "userLogin/{login}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByLogin(@PathVariable(name = "login") String login) {
        Optional<User> user = userService.findByLogin(login);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        else {
            return ResponseEntity.noContent().build();
        }

    }
}

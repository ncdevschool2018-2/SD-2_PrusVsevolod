package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long id);
    Iterable<User> getAllUsers();
    User saveUser(User user);
    void deleteUser(Long id);
    Optional<User> findByLogin(String login);

}

package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.UserViewModel;

import java.util.List;
import java.util.Optional;

public interface UserDataService {
    List<UserViewModel> getAll();
    Optional<UserViewModel> getUserById(Long id);
    UserViewModel saveUser(UserViewModel user);
    void deleteUser(Long id);
    UserViewModel findByLogin(String name);
}

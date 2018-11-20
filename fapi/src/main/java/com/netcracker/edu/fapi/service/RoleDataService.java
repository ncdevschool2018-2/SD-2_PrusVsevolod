package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.RoleViewModel;

import java.util.List;

public interface RoleDataService {
    List<RoleViewModel> getAll();
    RoleViewModel getRoleById(Long id);
}

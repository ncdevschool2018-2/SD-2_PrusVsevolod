package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.CategoryViewModel;

import java.util.List;

public interface CategoryDataService {
    List<CategoryViewModel> findAll();
}

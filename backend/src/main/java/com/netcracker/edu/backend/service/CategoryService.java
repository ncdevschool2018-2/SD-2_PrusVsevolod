package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Category;

public interface CategoryService {
    Iterable<Category> findAll();
}

package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.model.CategoryViewModel;
import com.netcracker.edu.fapi.service.CategoryDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;


    @Override
    public List<CategoryViewModel> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/category", List.class);
    }
}

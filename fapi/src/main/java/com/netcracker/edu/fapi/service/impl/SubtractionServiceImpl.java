package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.service.SubtractionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SubtractionServiceImpl implements SubtractionService {

    @Value("${backend.server.url}")
    private String backendServerUrl;


    @Override
    public void editThreshold(Integer value) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl + "/api/subtraction/threshold/" + value, Integer.class);
    }
}

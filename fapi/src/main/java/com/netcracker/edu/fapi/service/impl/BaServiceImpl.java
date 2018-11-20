package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.model.BaViewModel;
import com.netcracker.edu.fapi.service.BaDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class BaServiceImpl implements BaDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public void saveBa(BaViewModel ba) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "/api/billing_accounts/", ba, BaViewModel.class);
    }

    @Override
    public void saveEditedBa(BaViewModel ba) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl + "/api/billing_accounts/", ba, BaViewModel.class);
    }

    @Override
    public List<BaViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        BaViewModel[] baViewModelsResponse = restTemplate.getForObject(backendServerUrl + "/api/billing_accounts/", BaViewModel[].class);
        return baViewModelsResponse == null ? Collections.emptyList() : Arrays.asList(baViewModelsResponse);
    }

    @Override
    public BaViewModel getBaById(Long id) {
        return null;
    }

}

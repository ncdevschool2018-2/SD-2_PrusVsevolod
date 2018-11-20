package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.model.OwnerViewModel;
import com.netcracker.edu.fapi.service.OwnerDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<OwnerViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        OwnerViewModel[] ownerViewModelsResponse = restTemplate.getForObject(backendServerUrl + "/api/owners/", OwnerViewModel[].class);
        return ownerViewModelsResponse == null ? Collections.emptyList() : Arrays.asList(ownerViewModelsResponse);
    }

    @Override
    public Optional<OwnerViewModel> getOwnerById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/owners/" + id, Optional.class);
    }

    @Override
    public OwnerViewModel getOwnerByUserId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/owners/user/" + id, OwnerViewModel.class);
    }

    @Override
    public OwnerViewModel saveOwner(OwnerViewModel owner) {
        RestTemplate restTemplate = new RestTemplate();
//        System.out.println(owner);
        return restTemplate.postForEntity(backendServerUrl + "/api/owners", owner, OwnerViewModel.class).getBody();
    }

    @Override
    public void saveEditedOwner(OwnerViewModel owner) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.put(backendServerUrl + "/api/owners", owner, OwnerViewModel.class);
    }

    @Override
    public void deleteOwner(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/owners/" + id);
    }

    @Override
    public OwnerViewModel saveOwnerBa(OwnerViewModel owner) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/owners/ba", owner, OwnerViewModel.class).getBody();
    }
}

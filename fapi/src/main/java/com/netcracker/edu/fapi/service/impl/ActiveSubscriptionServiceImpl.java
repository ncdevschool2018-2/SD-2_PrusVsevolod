package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.model.ActiveSubscriptionModel;
import com.netcracker.edu.fapi.service.ActiveSubscriptionDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ActiveSubscriptionServiceImpl implements ActiveSubscriptionDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;


    @Override
    public List<ActiveSubscriptionModel> saveActiveSubscriptions(List<ActiveSubscriptionModel> activeSubscriptionModel) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/active_subscription", activeSubscriptionModel, List.class).getBody();
    }

    @Override
    public Iterable<ActiveSubscriptionModel> getASByCustomerId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/active_subscription/customer/" + id, Iterable.class);
    }

    @Override
    public ActiveSubscriptionModel getActiveSubscriptionById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/active_subscription/" + id, ActiveSubscriptionModel.class);
    }

    @Override
    public void deleteActiveSubscriptionById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/active_subscription/" + id);
    }
}

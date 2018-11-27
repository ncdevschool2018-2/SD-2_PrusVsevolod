package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.model.Content;
import com.netcracker.edu.fapi.model.SubscriptionViewModel;
import com.netcracker.edu.fapi.service.SubscriptionDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public Content<SubscriptionViewModel> findAll(int page, int size) {
        RestTemplate restTemplate = new RestTemplate();
        Content<SubscriptionViewModel> subscriptionsViewModelContent = restTemplate.getForObject(backendServerUrl + "/api/subscriptions?page=" + page + "&size=" + size, Content.class);
        return subscriptionsViewModelContent;
    }

    @Override
    public Content<SubscriptionViewModel> findByNameLike(String name, int page, int size) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/subscriptions/search?name=" + name + "&page=" + page + "&size=" + size, Content.class);
    }

    @Override
    public List<SubscriptionViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        SubscriptionViewModel[] subscriptionViewModelsResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions/", SubscriptionViewModel[].class);
        return subscriptionViewModelsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionViewModelsResponse);
    }

    @Override
    public SubscriptionViewModel getSubscriptionById(Long id) {
        return null;
    }

    @Override
    public List<SubscriptionViewModel> findByOwnerId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        SubscriptionViewModel[] subscriptionViewModelsResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions/owner/" + id, SubscriptionViewModel[].class);
        return subscriptionViewModelsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionViewModelsResponse);
    }

    @Override
    public SubscriptionViewModel saveSubscription(SubscriptionViewModel subscription) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/subscriptions", subscription, SubscriptionViewModel.class).getBody();
    }

    @Override
    public void deleteSubscription(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/subscriptions/" + id);
    }

}

package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.model.ShoppingBasketViewModel;
import com.netcracker.edu.fapi.service.ShoppingBasketDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ShoppingBasketServiceImpl implements ShoppingBasketDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;


    @Override
    public List<ShoppingBasketViewModel> findByCustomerId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/sb/customer/" + id, List.class);
    }

    @Override
    public Long getCount(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/sb/count/" + id, Long.class);
    }

    @Override
    public void saveSb(List<ShoppingBasketViewModel> Sb) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "/api/sb", Sb , List.class);
    }

    @Override
    public ShoppingBasketViewModel getSbById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/sb/" + id, ShoppingBasketViewModel.class);
    }

    @Override
    public void deleteShoppingBasketItem(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/sb/" + id);
    }

    @Override
    public void deleteSBByCustomerId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/sb/customer/" + id);
    }


}

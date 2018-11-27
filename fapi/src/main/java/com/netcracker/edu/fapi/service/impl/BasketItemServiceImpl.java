package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.model.BasketItemViewModel;
import com.netcracker.edu.fapi.service.BasketItemDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BasketItemServiceImpl implements BasketItemDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;


    @Override
    public List<BasketItemViewModel> findByCustomerId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/basket_item/customer/" + id, List.class);
    }

    @Override
    public Long getCount(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/basket_item/count/" + id, Long.class);
    }

    @Override
    public void saveBasketItem(List<BasketItemViewModel> Sb) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "/api/basket_item", Sb , List.class);
    }

    @Override
    public BasketItemViewModel getBasketItemById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/basket_item/" + id, BasketItemViewModel.class);
    }

    @Override
    public void deleteBasketItem(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/basket_item/" + id);
    }

    @Override
    public void deleteBasketItemByCustomerId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/basket_item/customer/" + id);
    }


}

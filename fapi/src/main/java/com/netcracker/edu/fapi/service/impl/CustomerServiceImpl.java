package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.model.Content;
import com.netcracker.edu.fapi.model.CustomerViewModel;
import com.netcracker.edu.fapi.service.CustomerDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomerDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public Content<CustomerViewModel> getAll(int page, int size) {
        RestTemplate restTemplate = new RestTemplate();
        Content<CustomerViewModel> customerViewModelsResponse = restTemplate.getForObject(backendServerUrl + "/api/customers?page=" + page + "&size=" + size, Content.class);
        return customerViewModelsResponse;
    }

    @Override
    public CustomerViewModel getCustomerById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/customers/" + id, CustomerViewModel.class);
    }

    @Override
    public CustomerViewModel getCustomerByUserId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/customers/user/" + id, CustomerViewModel.class);
    }


    @Override
    public CustomerViewModel saveCustomer(CustomerViewModel customer) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/customers", customer, CustomerViewModel.class).getBody();

    }

    @Override
    public void saveEditedCustomer(CustomerViewModel customer) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(backendServerUrl + "/api/customers", customer, CustomerViewModel.class);
    }

    @Override
    public void deleteCustomer(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/customers/" + id);
    }

    @Override
    public CustomerViewModel saveCustomerBa(CustomerViewModel customer) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/customers/ba", customer, CustomerViewModel.class).getBody();
    }

}

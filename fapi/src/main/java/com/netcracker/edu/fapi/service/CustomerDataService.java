package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.CustomerViewModel;

import java.util.List;

public interface CustomerDataService {
    List<CustomerViewModel> getAll();
    CustomerViewModel getCustomerById(Long id);
    CustomerViewModel getCustomerByUserId(Long id);
    CustomerViewModel saveCustomer(CustomerViewModel customer);
    void saveEditedCustomer(CustomerViewModel customer);
    CustomerViewModel saveCustomerBa(CustomerViewModel customer);
    void deleteCustomer(Long id);
}

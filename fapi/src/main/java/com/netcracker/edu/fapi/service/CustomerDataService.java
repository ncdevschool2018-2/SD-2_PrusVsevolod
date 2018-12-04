package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.Content;
import com.netcracker.edu.fapi.model.CustomerViewModel;

public interface CustomerDataService {
    Content<CustomerViewModel> getAll(int page, int size);
    CustomerViewModel getCustomerById(Long id);
    CustomerViewModel getCustomerByUserId(Long id);
    CustomerViewModel saveCustomer(CustomerViewModel customer);
    void saveEditedCustomer(CustomerViewModel customer);
    CustomerViewModel saveCustomerBa(CustomerViewModel customer);
    void deleteCustomer(Long id);
}

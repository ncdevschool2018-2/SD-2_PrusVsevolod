package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getCustomerById(Long id);
    Iterable<Customer> getAllCustomers(int page, int size);
    Customer saveCustomer(Customer customer);
    Customer saveEditedCustomer(Customer customer);
    void deleteCustomer(Long id);
    Customer findByUserId(Long id);

}

package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Customer;
import com.netcracker.edu.backend.repository.CustomerRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerServiceImpl implements CustomerService {

    private UserRepository userRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(UserRepository userRepository, CustomerRepository repository) {
        this.userRepository = userRepository;
        this.customerRepository = repository;
    }


    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        userRepository.save(customer.getUser());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Long UserId = getCustomerById(id).get().getUser().getId();
//        customersRepository.deleteById(id);
        userRepository.deleteById(UserId);
    }

    @Override
    public Customer findByUserId(Long id) {
        return customerRepository.findByUserId(id);
    }
}

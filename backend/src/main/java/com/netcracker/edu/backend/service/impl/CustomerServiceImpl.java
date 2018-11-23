package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Customer;
import com.netcracker.edu.backend.entity.Status;
import com.netcracker.edu.backend.repository.CustomerRepository;
import com.netcracker.edu.backend.repository.StatusRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerServiceImpl implements CustomerService {

    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private StatusRepository statusRepository;

    @Autowired
    public CustomerServiceImpl(UserRepository userRepository, CustomerRepository repository, StatusRepository statusRepository) {
        this.userRepository = userRepository;
        this.customerRepository = repository;
        this.statusRepository = statusRepository;
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
        customer.setStatus(statusRepository.findById(Long.valueOf(1)).get());
        userRepository.save(customer.getUser());
        return customerRepository.save(customer);
    }

    @Override
    public Customer saveEditedCustomer(Customer customer) {
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

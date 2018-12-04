package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Customer;
import com.netcracker.edu.backend.repository.CustomerRepository;
import com.netcracker.edu.backend.service.BillingAccountService;
import com.netcracker.edu.backend.service.CustomerService;
import com.netcracker.edu.backend.service.StatusService;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class CustomerServiceImpl implements CustomerService {

    private UserService userService;
    private CustomerRepository customerRepository;
    private StatusService statusService;
    private BillingAccountService billingAccountService;

    @Autowired
    public CustomerServiceImpl(UserService userService, CustomerRepository repository, StatusService statusService, BillingAccountService billingAccountService) {
        this.userService = userService;
        this.customerRepository = repository;
        this.statusService = statusService;
        this.billingAccountService = billingAccountService;
    }


    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Iterable<Customer> getAllCustomers(int page, int size) {
        return customerRepository.findAll(PageRequest.of(page,size));
    }

    @Transactional
    @Override
    public Customer saveCustomer(Customer customer) {
        customer.setStatus(statusService.getStatusById(Long.valueOf(1)).get());
        userService.saveUser(customer.getUser());
        return customerRepository.save(customer);
    }

    @Override
    public Customer saveEditedCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer deletedCustomer = getCustomerById(id).get();
        Long UserId = deletedCustomer.getUser().getId();
        if (deletedCustomer.getBa() != null) {
            billingAccountService.deleteBa(deletedCustomer.getBa());
        }
        userService.deleteUser(UserId);
    }

    @Override
    public Customer findByUserId(Long id) {
        return customerRepository.findByUserId(id);
    }
}

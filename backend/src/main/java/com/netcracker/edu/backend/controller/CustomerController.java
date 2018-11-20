package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Customer;
import com.netcracker.edu.backend.service.BillingAccountService;
import com.netcracker.edu.backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private CustomerService customerService;
    private BillingAccountService billingAccountService;

    @Autowired
    public CustomerController(CustomerService customerService, BillingAccountService billingAccountService) {
        this.customerService = customerService;
        this.billingAccountService = billingAccountService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        public ResponseEntity<Customer> getCustomerById(@PathVariable(name = "id") Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Customer saveEditedCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteCustomer(@PathVariable(name = "id") Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerByUserId(@PathVariable(name = "id") Long id) {
        Customer customer = customerService.findByUserId(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        else {
            return ResponseEntity.noContent().build();
        }

    }

    @RequestMapping(value = "/ba", method = RequestMethod.POST)
    public Customer saveBa(@RequestBody Customer customer){
        billingAccountService.saveBa(customer.getBa());
        return customerService.saveCustomer(customer);

    }
}

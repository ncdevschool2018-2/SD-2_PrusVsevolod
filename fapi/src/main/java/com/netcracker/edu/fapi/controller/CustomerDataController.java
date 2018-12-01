package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.model.BaViewModel;
import com.netcracker.edu.fapi.model.Constants;
import com.netcracker.edu.fapi.model.CustomerViewModel;
import com.netcracker.edu.fapi.service.BaDataService;
import com.netcracker.edu.fapi.service.CustomerDataService;
import com.netcracker.edu.fapi.service.UserDataService;
import com.netcracker.edu.fapi.transfer.Exist;
import com.netcracker.edu.fapi.transfer.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerDataController {

    @Autowired
    private CustomerDataService customerDataService;
    @Autowired
    private UserDataService userDataService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private BaDataService baDataService;

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping
    public ResponseEntity<List<CustomerViewModel>> getAllCustomers() {
        return ResponseEntity.ok(customerDataService.getAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CustomerViewModel> saveCustomer(@Validated(New.class) @RequestBody CustomerViewModel customer) {
        customer.getUser().setPassword(passwordEncoder.encode(customer.getUser().getPassword()));
        if (customer != null) {
            return ResponseEntity.ok(customerDataService.saveCustomer(customer));
        }
        return ResponseEntity.notFound().build();
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CustomerViewModel> saveEditedCustomer(@Validated(Exist.class) @RequestBody CustomerViewModel customer) {
        customerDataService.saveEditedCustomer(customer);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerViewModel> getCustomerById(@PathVariable(name = "id") Long id) {
        CustomerViewModel customer = customerDataService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('admin', 'customer')")
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<CustomerViewModel> getCustomerByUserId() {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable String id) {
        customerDataService.deleteCustomer(Long.valueOf(id));
    }

    @PreAuthorize("hasAnyAuthority('customer')")
    @RequestMapping(value = "/ba", method = RequestMethod.POST)
    public ResponseEntity<BaViewModel> saveBa(@Validated(New.class) @RequestBody BaViewModel ba) {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        if (customer.getBa() == null && ba.getNumber() != null) {
            customer.setBa(ba);
            return ResponseEntity.ok(customerDataService.saveCustomerBa(customer).getBa());
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyAuthority('customer')")
    @RequestMapping(value = "/ba/{value}", method = RequestMethod.PUT)
    public ResponseEntity<BaViewModel> saveEditedBa(@PathVariable(name = "value") int value) {
//        System.out.println(value);
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        if (customer.getBa() == null || value <= 0) {
            return ResponseEntity.badRequest().build();
        }
        customer.getBa().setBalance(customer.getBa().getBalance() + value);
        baDataService.saveEditedBa(customer.getBa());
        if(customer.getStatus().getName().equals("blocked") && customer.getBa().getBalance() > Constants.THRESHOLD){//Проверяем пополнил ли кастомер кошелек и если закрыл долг то делаем valid
            customer.getStatus().setId(1);
            customerDataService.saveEditedCustomer(customer);
        }

        return ResponseEntity.ok().build();
    }
}

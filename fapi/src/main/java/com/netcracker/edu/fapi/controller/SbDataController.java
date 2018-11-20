package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.model.CustomerViewModel;
import com.netcracker.edu.fapi.model.ShoppingBasketViewModel;
import com.netcracker.edu.fapi.service.CustomerDataService;
import com.netcracker.edu.fapi.service.ShoppingBasketDataService;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sb")
public class SbDataController {

    @Autowired
    private ShoppingBasketDataService shoppingBasketDataService;
    @Autowired
    private CustomerDataService customerDataService;
    @Autowired
    private UserDataService userDataService;

    @PreAuthorize("hasAnyAuthority('customer', 'admin')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<ShoppingBasketViewModel>> saveSb(@RequestBody List<ShoppingBasketViewModel> Sb) {
        return ResponseEntity.ok(shoppingBasketDataService.saveSb(Sb));
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ShoppingBasketViewModel> getSbById(@PathVariable(name = "id") Long id) {
        ShoppingBasketViewModel sb = shoppingBasketDataService.getSbById(id);
        if (sb != null) {
            return ResponseEntity.ok(sb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('customer')")
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ResponseEntity<List<ShoppingBasketViewModel>> getSbByCustomerId() {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        List<ShoppingBasketViewModel> sb = shoppingBasketDataService.findByCustomerId(customer.getId());
        if (sb != null) {
            return ResponseEntity.ok(sb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('admin', 'customer')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable String id) {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        ShoppingBasketViewModel basketItem = shoppingBasketDataService.getSbById(Long.valueOf(id));
        if (basketItem.getCustomerId().equals(customer.getId())) {//КАСТОМЕР МОЖЕТ УДАЛЯТЬ ТОЛЬКО СВОИ ПОДПИСКИ В КОРЗИНЕ
            shoppingBasketDataService.deleteShoppingBasketItem(Long.valueOf(id));
        }
    }

}

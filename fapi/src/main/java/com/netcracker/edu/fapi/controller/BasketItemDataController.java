package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.model.BasketItemViewModel;
import com.netcracker.edu.fapi.model.CustomerViewModel;
import com.netcracker.edu.fapi.model.WrapperList;
import com.netcracker.edu.fapi.service.BasketItemDataService;
import com.netcracker.edu.fapi.service.CustomerDataService;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/basket_item")
public class BasketItemDataController {

    @Autowired
    private BasketItemDataService basketItemDataService;
    @Autowired
    private CustomerDataService customerDataService;
    @Autowired
    private UserDataService userDataService;

    @PreAuthorize("hasAnyAuthority('customer')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveBasketItem(@Valid @RequestBody WrapperList<BasketItemViewModel> Sb) {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        if (customer.getId().equals(Sb.getWrapperList().get(0).getCustomerId())) {//Для случая если недобросовестный кастомер захочет добавить в корзину товар другому кастомеру
            basketItemDataService.saveBasketItem(Sb.getWrapperList());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BasketItemViewModel> getBasketItemById(@PathVariable(name = "id") Long id) {
        BasketItemViewModel sb = basketItemDataService.getBasketItemById(id);
        if (sb != null) {
            return ResponseEntity.ok(sb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('customer')")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ResponseEntity<Long> getCount() {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        return ResponseEntity.ok(basketItemDataService.getCount(customer.getId()));
    }

    @PreAuthorize("hasAnyAuthority('customer')")
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ResponseEntity<List<BasketItemViewModel>> getBasketItemByCustomerId() {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        List<BasketItemViewModel> sb = basketItemDataService.findByCustomerId(customer.getId());
        if (sb != null) {
            return ResponseEntity.ok(sb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyAuthority('customer')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBasketItem(@PathVariable Long id) {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        BasketItemViewModel basketItem = basketItemDataService.getBasketItemById(id);
        if (basketItem.getCustomerId().equals(customer.getId())) {//КАСТОМЕР МОЖЕТ УДАЛЯТЬ ТОЛЬКО СВОИ ПОДПИСКИ В КОРЗИНЕ
            basketItemDataService.deleteBasketItem(id);
        }
    }

    @PreAuthorize("hasAnyAuthority('customer')")
    @RequestMapping(value = "/customer",method = RequestMethod.DELETE)
    public void deleteBasketItemByCustomerId() {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        basketItemDataService.deleteBasketItemByCustomerId(customer.getId());
    }
}

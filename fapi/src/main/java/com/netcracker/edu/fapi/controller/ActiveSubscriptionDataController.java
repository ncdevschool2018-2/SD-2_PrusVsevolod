package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.model.ActiveSubscriptionModel;
import com.netcracker.edu.fapi.model.CustomerViewModel;
import com.netcracker.edu.fapi.model.WrapperList;
import com.netcracker.edu.fapi.service.ActiveSubscriptionDataService;
import com.netcracker.edu.fapi.service.CustomerDataService;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/active_subscription")
public class ActiveSubscriptionDataController {

    @Autowired
    private ActiveSubscriptionDataService activeSubscriptionDataService;
    @Autowired
    private CustomerDataService customerDataService;
    @Autowired
    private UserDataService userDataService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<ActiveSubscriptionModel>> saveActiveSubscription(@Valid @RequestBody WrapperList<ActiveSubscriptionModel> activeSubscriptionModels) {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        for (ActiveSubscriptionModel subscription : activeSubscriptionModels.getWrapperList()) {
            subscription.setLastEditDate(System.currentTimeMillis());
            subscription.setCustomerId(customer.getId());
        }
        return ResponseEntity.ok(activeSubscriptionDataService.saveActiveSubscriptions(activeSubscriptionModels.getWrapperList()));
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ActiveSubscriptionModel>> getAllASByCustomerId() {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        return ResponseEntity.ok(activeSubscriptionDataService.getASByCustomerId(customer.getId()));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteActiveSubscription(@PathVariable(name = "id") Long id) {
        CustomerViewModel customer = customerDataService.getCustomerByUserId(Long.valueOf(userDataService.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
        ActiveSubscriptionModel activeSubscription = activeSubscriptionDataService.getActiveSubscriptionById(id);
        if (activeSubscription.getCustomerId().equals(customer.getId())) {//Кастомер может удалять только свои активные подписки
            activeSubscriptionDataService.deleteActiveSubscriptionById(activeSubscription.getId());
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }

}

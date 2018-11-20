package com.netcracker.edu.fapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.transfer.Exist;
import com.netcracker.edu.fapi.transfer.New;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShoppingBasketViewModel {

    @Null(groups = {New.class})
    private Long id;
    @NotNull(groups = {New.class, Exist.class})
    private Long customerId;
    @NotNull(groups = {New.class, Exist.class})
    @Valid
    private SubscriptionViewModel subscription;
    @NotNull(groups = {New.class, Exist.class})
    @Min(groups = {New.class, Exist.class}, value = 1)
    @Max(groups = {New.class, Exist.class}, value = 99)
    private Integer quantity;

    public ShoppingBasketViewModel() {
    }

    public ShoppingBasketViewModel(Long id, long customerId, SubscriptionViewModel subscription, Integer quantity) {
        this.id = id;
        this.customerId = customerId;
        this.subscription = subscription;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public SubscriptionViewModel getSubscription() {
        return subscription;
    }

    public void setSubscription(SubscriptionViewModel subscription) {
        this.subscription = subscription;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

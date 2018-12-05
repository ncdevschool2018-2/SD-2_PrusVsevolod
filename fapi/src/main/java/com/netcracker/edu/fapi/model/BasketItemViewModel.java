package com.netcracker.edu.fapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BasketItemViewModel {

    @Null
    private Long id;
    @NotNull
    private Long customerId;
    @NotNull
    @Valid
    private SubscriptionViewModel subscription;
    @NotNull
    @Min(value = 1)
    private Integer quantity;

    public BasketItemViewModel() {
    }

    public BasketItemViewModel(Long id, long customerId, SubscriptionViewModel subscription, Integer quantity) {
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

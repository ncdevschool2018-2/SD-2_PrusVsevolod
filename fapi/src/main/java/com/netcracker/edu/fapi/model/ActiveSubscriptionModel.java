package com.netcracker.edu.fapi.model;

import java.util.Date;

public class ActiveSubscriptionModel {

    private Long id;
    private Date activationDate;
    private Long customerId;
    private SubscriptionViewModel subscription;
    private Integer quantity;

    public ActiveSubscriptionModel() {
    }

    public ActiveSubscriptionModel(Long id, Date activationDate, Long customerId, SubscriptionViewModel subscription, Integer quantity) {
        this.id = id;
        this.activationDate = activationDate;
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

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
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

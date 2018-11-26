package com.netcracker.edu.fapi.model;

public class ActiveSubscriptionModel {

    private Long id;
    private Long lastEditDate;
    private Long customerId;
    private SubscriptionViewModel subscription;
    private Integer quantity;

    public ActiveSubscriptionModel() {
    }

    public ActiveSubscriptionModel(Long id, Long lastEditDate, Long customerId, SubscriptionViewModel subscription, Integer quantity) {
        this.id = id;
        this.lastEditDate = lastEditDate;
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

    public Long getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Long lastEditDate) {
        this.lastEditDate = lastEditDate;
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

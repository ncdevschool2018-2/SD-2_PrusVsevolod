package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "active_subscription")
public class ActiveSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "activation_date")
    private Date activationDate;
//    @ManyToOne
//    @JoinColumn(name = "customer_id", nullable = false)
    @Column(name = "customer_id")
    private long customerId;
    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;
    private int quantity;

    public ActiveSubscription() {
    }

    public ActiveSubscription(Date activationDate, long customerId, Subscription subscription, int quantity) {
        this.activationDate = activationDate;
        this.customerId = customerId;
        this.subscription = subscription;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveSubscription that = (ActiveSubscription) o;
        return id == that.id &&
                customerId == that.customerId &&
                quantity == that.quantity &&
                Objects.equals(activationDate, that.activationDate) &&
                Objects.equals(subscription, that.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activationDate, customerId, subscription, quantity);
    }

    @Override
    public String toString() {
        return "ActiveSubscription{" +
                "id=" + id +
                ", activationDate=" + activationDate +
                ", customerId=" + customerId +
                ", subscription=" + subscription +
                ", quantity=" + quantity +
                '}';
    }
}


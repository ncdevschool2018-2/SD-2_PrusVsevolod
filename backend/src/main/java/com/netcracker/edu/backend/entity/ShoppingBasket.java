package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "shopping_basket")
public class ShoppingBasket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "customer_id")
    private long customerId;
    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
    private int quantity;

    public ShoppingBasket() {
    }

    public ShoppingBasket(long customerId, Subscription subscription, int quantity) {
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
        ShoppingBasket that = (ShoppingBasket) o;
        return id == that.id &&
                customerId == that.customerId &&
                quantity == that.quantity &&
                Objects.equals(subscription, that.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, subscription, quantity);
    }

    @Override
    public String toString() {
        return "ShoppingBasket{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", subscription=" + subscription +
                ", quantity=" + quantity +
                '}';
    }
}

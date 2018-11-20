package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer_subscription")
public class CustomerToSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String activation_date;
    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public CustomerToSubscription() {
    }

    public CustomerToSubscription(String activation_date, Subscription subscription, Customer customer) {
        this.activation_date = activation_date;
        this.subscription = subscription;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActivation_date() {
        return activation_date;
    }

    public void setActivation_date(String activation_date) {
        this.activation_date = activation_date;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerToSubscription that = (CustomerToSubscription) o;
        return id == that.id &&
                Objects.equals(activation_date, that.activation_date) &&
                Objects.equals(subscription, that.subscription) &&
                Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activation_date, subscription, customer);
    }

    @Override
    public String toString() {
        return "CustomerToSubscription{" +
                "id=" + id +
                ", activation_date='" + activation_date + '\'' +
                ", subscription=" + subscription +
                ", customer=" + customer +
                '}';
    }
}


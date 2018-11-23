package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToOne
    @JoinColumn(name = "ba_id")
    private BillingAccount ba;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    public Customer() {
    }

    public Customer(String name, String address, User user, BillingAccount ba, Status status) {
        this.name = name;
        this.address = address;
        this.user = user;
        this.ba = ba;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BillingAccount getBa() {
        return ba;
    }

    public void setBa(BillingAccount ba) {
        this.ba = ba;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(user, customer.user) &&
                Objects.equals(ba, customer.ba) &&
                Objects.equals(status, customer.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, user, ba, status);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                ", ba=" + ba +
                ", status=" + status +
                '}';
    }
}

package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "billing_account")
public class BillingAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private Integer cvv;
    private String validity;
    private Integer balance;

    public BillingAccount() {
    }

    public BillingAccount(String number, Integer cvv, String validity, Integer balance) {
        this.number = number;
        this.cvv = cvv;
        this.validity = validity;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingAccount that = (BillingAccount) o;
        return id == that.id &&
                Objects.equals(number, that.number) &&
                Objects.equals(cvv, that.cvv) &&
                Objects.equals(validity, that.validity) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, cvv, validity, balance);
    }

    @Override
    public String toString() {
        return "BillingAccount{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", cvv=" + cvv +
                ", validity='" + validity + '\'' +
                ", balance=" + balance +
                '}';
    }
}

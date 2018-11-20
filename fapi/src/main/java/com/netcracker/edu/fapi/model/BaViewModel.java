package com.netcracker.edu.fapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.transfer.Exist;
import com.netcracker.edu.fapi.transfer.New;

import javax.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaViewModel {
    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    private Long id;
    @NotNull(groups = {New.class, Exist.class})
    @Size(groups = {New.class, Exist.class}, min = 16, max = 16)
    @Pattern(groups = {New.class, Exist.class}, regexp="^[0-9]+$")
    private String number;
    @NotNull(groups = {New.class, Exist.class})
    @Min(groups = {New.class, Exist.class}, value = 100)
    @Max(groups = {New.class, Exist.class}, value = 999)
    private Integer cvv;
    @NotNull(groups = {New.class, Exist.class})
    @Size(groups = {New.class, Exist.class}, min = 4, max = 20)
    private String validity;
    @Min(groups = {New.class, Exist.class}, value = 0)
    private int balance;


    public BaViewModel() {
    }

    public BaViewModel(Long id, String number, Integer cvv, String validity, int balance) {
        this.id = id;
        this.number = number;
        this.cvv = cvv;
        this.validity = validity;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

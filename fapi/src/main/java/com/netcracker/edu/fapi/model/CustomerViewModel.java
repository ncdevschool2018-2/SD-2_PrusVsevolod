package com.netcracker.edu.fapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.transfer.Exist;
import com.netcracker.edu.fapi.transfer.New;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerViewModel {
    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    private Long id;
    @NotNull(groups = {New.class, Exist.class})
    @Size(groups = {New.class, Exist.class}, min = 6, max = 20)
    @Pattern(groups = {New.class, Exist.class}, regexp="^[A-z]+$")
    private String name;
    @NotNull(groups = {New.class, Exist.class})
    @Size(groups = {New.class, Exist.class}, min = 6, max = 25)
    @Pattern(groups = {New.class, Exist.class}, regexp="^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$")
    private String address;
    @NotNull(groups = {New.class, Exist.class})
    @Valid
    private UserViewModel user;
    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    @Valid
    private BaViewModel ba;
    @NotNull(groups = {Exist.class})
    private StatusViewModel status;

    public CustomerViewModel() {
    }

    public CustomerViewModel(Long id,  String name, String address, UserViewModel user, BaViewModel ba, StatusViewModel status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.user = user;
        this.ba = ba;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public UserViewModel getUser() {
        return user;
    }

    public void setUser(UserViewModel user) {
        this.user = user;
    }

    public BaViewModel getBa() {
        return ba;
    }

    public void setBa(BaViewModel ba) {
        this.ba = ba;
    }

    public StatusViewModel getStatus() {
        return status;
    }

    public void setStatus(StatusViewModel status) {
        this.status = status;
    }
}

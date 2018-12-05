package com.netcracker.edu.fapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.transfer.Exist;
import com.netcracker.edu.fapi.transfer.New;

import javax.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserViewModel {

    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    private String id;
    @NotNull(groups = {New.class, Exist.class})
    @Size(groups = {New.class}, min = 6, max = 20)
    @Pattern(groups = {New.class}, regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$")
    private String login;
    @Email(groups = {New.class,Exist.class})
    private String email;
    @NotNull(groups = {New.class, Exist.class})
    @Pattern(groups = {New.class}, regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$")
    @Size(groups = {New.class}, min = 6, max = 20)
    private String password;
    @NotNull(groups = {New.class, Exist.class})
    private RoleViewModel role;

    public UserViewModel() {
    }

    public UserViewModel(String id, String login, String email, String password, RoleViewModel role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleViewModel getRole() {
        return role;
    }

    public void setRole(RoleViewModel role) {
        this.role = role;
    }
}

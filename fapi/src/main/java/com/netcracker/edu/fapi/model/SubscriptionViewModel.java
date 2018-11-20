package com.netcracker.edu.fapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netcracker.edu.fapi.transfer.Exist;
import com.netcracker.edu.fapi.transfer.New;

import javax.validation.Valid;
import javax.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionViewModel {

    @Null(groups = {New.class})
    @NotNull(groups = {Exist.class})
    private Long id;
    @NotNull(groups = {New.class, Exist.class})
    @Size(groups = {New.class, Exist.class}, min = 4, max = 20)
    @Pattern(groups = {New.class, Exist.class}, regexp="^[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$")
    private String name;
    @NotNull(groups = {New.class, Exist.class})
    private String imageUrl;
    @NotNull(groups = {New.class, Exist.class})
    @Size(groups = {New.class, Exist.class}, min = 6, max = 100)
    private String description;
    @Min(groups = {New.class, Exist.class}, value = 1)
    @Max(groups = {New.class, Exist.class}, value = 999)
    private int price;
    @NotNull(groups = {New.class, Exist.class})
    @Valid
    private OwnerViewModel owner;

    public SubscriptionViewModel() {
    }

    public SubscriptionViewModel(Long id, String name, String imageUrl, String description, int price, OwnerViewModel owner) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.owner = owner;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public OwnerViewModel getOwner() {
        return owner;
    }

    public void setOwner(OwnerViewModel owner) {
        this.owner = owner;
    }
}

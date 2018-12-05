package com.netcracker.edu.fapi.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class WrapperList<T> {

    @NotEmpty(message="At least one item is required")
    @Valid
    private List<T> wrapperList;

    public WrapperList() {
    }

    public WrapperList(@NotEmpty(message = "At least one item is required") @Valid List<T> wrapperList) {
        this.wrapperList = wrapperList;
    }

    public List<T> getWrapperList() {
        return wrapperList;
    }

    public void setWrapperList(List<T> wrapperList) {
        this.wrapperList = wrapperList;
    }
}

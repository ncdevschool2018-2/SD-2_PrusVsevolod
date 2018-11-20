package com.netcracker.edu.fapi.model;

public class SortImpl {
    private boolean unsorted;
    private boolean sorted;

    public SortImpl() {
    }

    public SortImpl(boolean unsorted, boolean sorted) {
        this.unsorted = unsorted;
        this.sorted = sorted;
    }

    public boolean isUnsorted() {
        return unsorted;
    }

    public void setUnsorted(boolean unsorted) {
        this.unsorted = unsorted;
    }

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
    }
}

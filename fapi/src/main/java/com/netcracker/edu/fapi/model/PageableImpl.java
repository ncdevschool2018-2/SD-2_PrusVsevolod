package com.netcracker.edu.fapi.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableImpl {

    private SortImpl sort;
    private int offset;
    private int pageSize;
    private int pageNumber;
    private boolean paged;
    private boolean unpaged;

    public PageableImpl() {
    }

    public PageableImpl(SortImpl sort, int offset, int pageSize, int pageNumber, boolean paged, boolean unpaged) {
        this.sort = sort;
        this.offset = offset;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.paged = paged;
        this.unpaged = unpaged;
    }

    public SortImpl getSort() {
        return sort;
    }

    public void setSort(SortImpl sort) {
        this.sort = sort;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public boolean isPaged() {
        return paged;
    }

    public void setPaged(boolean paged) {
        this.paged = paged;
    }

    public boolean isUnpaged() {
        return unpaged;
    }

    public void setUnpaged(boolean unpaged) {
        this.unpaged = unpaged;
    }
}

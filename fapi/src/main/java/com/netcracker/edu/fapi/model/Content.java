package com.netcracker.edu.fapi.model;

public class Content<T> {

    private T []content;
    private PageableImpl pageable;
    private boolean last;
    private int totalPages;
    private int totalElements;
    private int size;
    private int number;
    private SortImpl sort;
    private int numberOfElements;
    private boolean first;

    public Content() {
    }

    public Content(T[] content, PageableImpl pageable, boolean last, int totalPages, int totalElements, int size, int number, SortImpl sort, int numberOfElements, boolean first) {
        this.content = content;
        this.pageable = pageable;
        this.last = last;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.number = number;
        this.sort = sort;
        this.numberOfElements = numberOfElements;
        this.first = first;
    }

    public T[] getContent() {
        return content;
    }

    public void setContent(T[] content) {
        this.content = content;
    }

    public PageableImpl getPageable() {
        return pageable;
    }

    public void setPageable(PageableImpl pageable) {
        this.pageable = pageable;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public SortImpl getSort() {
        return sort;
    }

    public void setSort(SortImpl sort) {
        this.sort = sort;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }
}

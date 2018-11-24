package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.ShoppingBasketViewModel;

import java.util.List;

public interface ShoppingBasketDataService {

    List<ShoppingBasketViewModel> findByCustomerId(Long id);
    Long getCount(Long id);
    void saveSb(List<ShoppingBasketViewModel> Sb);
    ShoppingBasketViewModel getSbById(Long id);
    void deleteShoppingBasketItem(Long id);
    void deleteSBByCustomerId(Long id);
}

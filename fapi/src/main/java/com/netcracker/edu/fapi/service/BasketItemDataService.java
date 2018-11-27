package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.model.BasketItemViewModel;

import java.util.List;

public interface BasketItemDataService {

    List<BasketItemViewModel> findByCustomerId(Long id);
    Long getCount(Long id);
    void saveBasketItem(List<BasketItemViewModel> Sb);
    BasketItemViewModel getBasketItemById(Long id);
    void deleteBasketItem(Long id);
    void deleteBasketItemByCustomerId(Long id);
}

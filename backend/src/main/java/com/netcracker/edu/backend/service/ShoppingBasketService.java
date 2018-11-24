package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.ShoppingBasket;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ShoppingBasketService {
    Long getCount(Long id);
    Iterable<ShoppingBasket> findByCustomerId(Long id);
    void saveSb(List<ShoppingBasket> Sb);
    Optional<ShoppingBasket> getShoppingBasketById(Long id);
    void deleteShoppingItem(Long id);
    @Transactional
    void deleteAllShoppingItemsByCustomerId(Long id);
}

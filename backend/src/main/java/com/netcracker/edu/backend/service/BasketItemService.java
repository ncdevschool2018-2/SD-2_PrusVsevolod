package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.BasketItem;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface BasketItemService {
    Long getCount(Long id);
    Iterable<BasketItem> findByCustomerId(Long id);
    void saveBasketItems(List<BasketItem> Sb);
    Optional<BasketItem> getBasketItemById(Long id);
    void deleteBasketItem(Long id);
    @Transactional
    void deleteAllBasketItemsByCustomerId(Long id);
}

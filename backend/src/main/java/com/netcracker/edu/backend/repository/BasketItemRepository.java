package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.BasketItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketItemRepository extends CrudRepository<BasketItem, Long> {

    Iterable<BasketItem> findByCustomerId(Long id);
    Long countByCustomerId(Long id);
    List<BasketItem> findAll();
    void deleteByCustomerId(Long id);
}

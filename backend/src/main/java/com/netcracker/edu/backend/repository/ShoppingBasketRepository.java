package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.ShoppingBasket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingBasketRepository extends CrudRepository<ShoppingBasket, Long> {

    Iterable<ShoppingBasket> findByCustomerId(Long id);
    List<ShoppingBasket> findAll();
    void deleteByCustomerId(Long id);
}

package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.ShoppingBasket;
import com.netcracker.edu.backend.repository.ShoppingBasketRepository;
import com.netcracker.edu.backend.service.ShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ShoppingBasketServiceImpl implements ShoppingBasketService {

    private ShoppingBasketRepository repository;

    @Autowired
    public ShoppingBasketServiceImpl(ShoppingBasketRepository repository) {
        this.repository = repository;
    }


    @Override
    public Iterable<ShoppingBasket> findByCustomerId(Long id) {
        return repository.findByCustomerId(id);
    }

    @Override
    public Iterable<ShoppingBasket> saveSb(List<ShoppingBasket> Sb) {
        List<ShoppingBasket> sameItems = new ArrayList<>();
        List<ShoppingBasket> basket = repository.findAll();
        for(ShoppingBasket basketItem: basket){
            for(ShoppingBasket sbItem: Sb){
                if(sbItem.getSubscription().getId() == basketItem.getSubscription().getId()){

                    basketItem.setQuantity(basketItem.getQuantity()+sbItem.getQuantity());
                    repository.save(basketItem);

                    sameItems.add(sbItem);
                }
            }
        }
        Sb.removeAll(sameItems);
        return repository.saveAll(Sb);
    }

    @Override
    public Optional<ShoppingBasket> getShoppingBasketById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteShoppingItem(Long id) {
        repository.deleteById(id);
    }
}

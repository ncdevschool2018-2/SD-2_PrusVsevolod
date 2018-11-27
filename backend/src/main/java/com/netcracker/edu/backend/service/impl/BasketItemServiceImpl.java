package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.BasketItem;
import com.netcracker.edu.backend.repository.BasketItemRepository;
import com.netcracker.edu.backend.service.BasketItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BasketItemServiceImpl implements BasketItemService {

    private BasketItemRepository repository;

    @Autowired
    public BasketItemServiceImpl(BasketItemRepository repository) {
        this.repository = repository;
    }


    @Override
    public Long getCount(Long id) {
        return repository.countByCustomerId(id);
    }

    @Override
    public Iterable<BasketItem> findByCustomerId(Long id) {
        return repository.findByCustomerId(id);
    }

    @Override
    public void saveBasketItems(List<BasketItem> Sb) {
        List<BasketItem> sameItems = new ArrayList<>();
        List<BasketItem> basket = (List<BasketItem>)repository.findByCustomerId(Sb.get(0).getCustomerId());
        for(BasketItem basketItem: basket){
            for(BasketItem sbItem: Sb){
                if(sbItem.getSubscription().getId() == basketItem.getSubscription().getId() && sbItem.getCustomerId() == basketItem.getCustomerId()){

                    basketItem.setQuantity(basketItem.getQuantity()+sbItem.getQuantity());
                    repository.save(basketItem);

                    sameItems.add(sbItem);
                }
            }
        }
        Sb.removeAll(sameItems);
        repository.saveAll(Sb);
    }

    @Override
    public Optional<BasketItem> getBasketItemById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteBasketItem(Long id) {
        repository.deleteById(id);
    }

    @Transactional// Все методы кроме read(find) которые мы переопределяем должны быть transactional0
    @Override
    public void deleteAllBasketItemsByCustomerId(Long id) {
        repository.deleteByCustomerId(id);
    }
}

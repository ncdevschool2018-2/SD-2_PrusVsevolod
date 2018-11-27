package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.BasketItem;
import com.netcracker.edu.backend.service.BasketItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/basket_item")
public class BasketItemController {

    private BasketItemService basketItemService;

    @Autowired
    public BasketItemController(BasketItemService shoppingBasketService){
        this.basketItemService = shoppingBasketService;
    }

    @RequestMapping(value = "/count/{id}", method = RequestMethod.GET)
    public ResponseEntity<Long> getCount(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(basketItemService.getCount(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BasketItem> getBasketItemById(@PathVariable(name = "id") Long id) {
        Optional<BasketItem> sb = basketItemService.getBasketItemById(id);
        if (sb.isPresent()) {
            return ResponseEntity.ok(sb.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveSb(@RequestBody List<BasketItem> Sb){
        basketItemService.saveBasketItems(Sb);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<BasketItem>> getBasketItemByCustomerId(@PathVariable(name = "id") Long id) {
        Iterable<BasketItem> sb = basketItemService.findByCustomerId(id);
        if (sb != null) {
            return ResponseEntity.ok(sb);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteBasketItemById(@PathVariable(name = "id") Long id){
        basketItemService.deleteBasketItem(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAllBasketItemsByCustomerId(@PathVariable(name = "id") Long id){
        basketItemService.deleteAllBasketItemsByCustomerId(id);
        return ResponseEntity.noContent().build();
    }
}

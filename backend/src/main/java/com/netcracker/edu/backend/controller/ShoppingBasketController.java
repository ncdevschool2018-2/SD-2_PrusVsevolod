package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.ShoppingBasket;
import com.netcracker.edu.backend.service.ShoppingBasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sb")
public class ShoppingBasketController {

    private ShoppingBasketService shoppingBasketService;

    @Autowired
    public ShoppingBasketController(ShoppingBasketService shoppingBasketService){
        this.shoppingBasketService = shoppingBasketService;
    }

    @RequestMapping(value = "/count/{id}", method = RequestMethod.GET)
    public ResponseEntity<Long> getCount(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(shoppingBasketService.getCount(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ShoppingBasket> getSbById(@PathVariable(name = "id") Long id) {
        Optional<ShoppingBasket> sb = shoppingBasketService.getShoppingBasketById(id);
        if (sb.isPresent()) {
            return ResponseEntity.ok(sb.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveSb(@RequestBody List<ShoppingBasket> Sb){
        shoppingBasketService.saveSb(Sb);
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<ShoppingBasket>> getSbByCustomerId(@PathVariable(name = "id") Long id) {
        Iterable<ShoppingBasket> sb = shoppingBasketService.findByCustomerId(id);
        if (sb != null) {
            return ResponseEntity.ok(sb);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteSbById(@PathVariable(name = "id") Long id){
        shoppingBasketService.deleteShoppingItem(id);
        return ResponseEntity.noContent().build();
    }
    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAllSbByCustomerId(@PathVariable(name = "id") Long id){
        shoppingBasketService.deleteAllShoppingItemsByCustomerId(id);
        return ResponseEntity.noContent().build();
    }
}

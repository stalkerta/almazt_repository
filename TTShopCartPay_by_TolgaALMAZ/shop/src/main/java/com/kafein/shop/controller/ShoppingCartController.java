package com.kafein.shop.controller;

import com.kafein.shop.entity.Item;
import com.kafein.shop.entity.ShoppingCart;
import com.kafein.shop.service.ShopService;
import com.kafein.shop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/shop/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    ShopService shopService;

    @PostMapping("/new")
    public ResponseEntity<ShoppingCart> create(@RequestBody ShoppingCart shoppingCart) {
        try {
            return new ResponseEntity<>(shoppingCartService.create(shoppingCart), HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<ShoppingCart> addItemsToCart(@PathVariable Long id, @RequestBody List<Item> itemList) {
        try {
            return new ResponseEntity<>(shoppingCartService.addToCart(id, itemList), HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/remove")
    public ResponseEntity<ShoppingCart> removeItemsFromCart(@PathVariable Long id, @RequestBody List<Item> itemList) {
        try {
            return new ResponseEntity<>(shoppingCartService.removeFromCart(id, itemList), HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(shoppingCartService.getCart(id), HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Tolga ALMAZ:Pay controller and payCart service is introduced to pay the shopping cart from the most suitable wallet/wallets
    @GetMapping("/pay/{cartID}/{userID}")
    public ResponseEntity<String> payCart(@PathVariable Long cartID, @PathVariable Long userID) {
        try {
            return new ResponseEntity<>(shopService.payCart(cartID,userID), HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    
}

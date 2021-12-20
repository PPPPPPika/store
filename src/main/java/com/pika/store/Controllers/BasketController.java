package com.pika.store.Controllers;

import com.pika.store.Models.Basket;
import com.pika.store.Services.BasketServices.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
@RequestMapping("/shop/basket")
public class BasketController {
    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/{userId}")
    public Flux<Basket> getBasket(@PathVariable Long userId){
        return basketService.getElements(userId);
    }

    @PostMapping("/{userId}/{clothesId}")
    public Mono<Basket> postClothesInBasket(@PathVariable Long userId, @PathVariable Long clothesId){
        return basketService.postElement(userId, clothesId);
    }

    @DeleteMapping("/{userId}/{clothesId}")
    public Mono<Void> deleteClotheFromBasket(@PathVariable Long userId, @PathVariable Long clothesId){
        return basketService.deleteElement(userId, clothesId);
    }

    @PatchMapping("/{userId}")
    public Mono<Void> buyClothes(@PathVariable Long userId, @RequestBody Long[] itemsArray){
        Arrays.stream(itemsArray).forEach(System.out::println);
        return basketService.buyClothes(userId, itemsArray);
    }

}

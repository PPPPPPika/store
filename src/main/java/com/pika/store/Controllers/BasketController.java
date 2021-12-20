package com.pika.store.Controllers;

import com.pika.store.Models.Basket;
import com.pika.store.Services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Mono<ResponseEntity<Basket>> postClothesInBasket(@PathVariable Long userId, @PathVariable Long clothesId){
        return basketService.postElement(userId, clothesId)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{userId}/{clothesId}")
    public Mono<ResponseEntity<Void>> deleteClotheFromBasket(@PathVariable Long userId, @PathVariable Long clothesId){
        return basketService.deleteElement(userId, clothesId)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}")
    public Mono<ResponseEntity<Void>> buyClothes(@PathVariable Long userId, @RequestBody Long[] itemsArray){
        return basketService.buyClothes(userId, itemsArray)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}

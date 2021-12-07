package com.pika.store.Controllers;

import com.pika.store.Models.Basket;
import com.pika.store.Services.BasketServices.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{idUser}")
    public Flux<Basket> getBasket(@PathVariable Long idUser){
        return basketService.getElements(idUser);
    }

    @PostMapping("/{userId}/{clothesId}")
    public Mono<Basket> postClothesInBasket(@PathVariable Long userId, @PathVariable Long clothesId){
        return basketService.postElement(userId, clothesId);
    }

    @DeleteMapping("/{userId}/{clothesId}")
    public Mono<Void> deleteClotheFromBasket(@PathVariable Long userId, @PathVariable Long clothesId){
        return basketService.deleteElement(userId, clothesId);
    }

}

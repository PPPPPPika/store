package com.pika.store.Controllers;

import com.pika.store.Models.Basket;
import com.pika.store.Services.BasketServices.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public Mono<Basket> getBasket(@PathVariable Long idUser){
        return basketService.getElement(idUser); //возможно понадобится выводить список предметов в корзине
    }

    @PostMapping("/{userId}/{clothesId}")
    public Mono<Basket> postClothesInBasket(@PathVariable Long userId, @PathVariable Long clothesId){
        return basketService.postElement(userId, clothesId);
    }

    //@DeleteMapping("")


}

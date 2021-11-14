package com.pika.store.Controllers;

import com.pika.store.Models.Clothes;
import com.pika.store.Services.ClothesServices.ClothesPagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/shop/clothes")
public class ClothesPagesController {
    private final ClothesPagesService clothesPagesService;

    @Autowired
    public ClothesPagesController(ClothesPagesService clothesPagesService) {
        this.clothesPagesService = clothesPagesService;
    }

    @GetMapping("/{genus}")
    public Flux<Clothes> getAllManClothes(@PathVariable String genus){
        return clothesPagesService.getAllElements(genus);
    }

    @GetMapping("/{genus}/{id}")
    public Mono<Clothes> getClothes(@PathVariable String genus, @PathVariable Long id){
        return clothesPagesService.getElement(genus, id);
    }



}

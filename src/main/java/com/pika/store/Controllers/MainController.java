package com.pika.store.Controllers;

import com.pika.store.Models.Clothes;
import com.pika.store.Repositorys.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/shop")
public class MainController {
    private final ClothesRepository ClothesRepository;

    @Autowired
    public MainController(ClothesRepository ClothesRepository) {
        this.ClothesRepository = ClothesRepository;
    }

    @GetMapping("/main")
    public Flux<Clothes> getAllClothes(){
        return ClothesRepository.findAll();
    }
}

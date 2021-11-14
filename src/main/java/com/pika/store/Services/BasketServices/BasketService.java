package com.pika.store.Services.BasketServices;

import com.pika.store.Models.Basket;
import com.pika.store.Models.Clothes;
import com.pika.store.Repositorys.BasketRepository;
import com.pika.store.Repositorys.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final ClothesRepository clothesRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository,
                         ClothesRepository clothesRepository) {
        this.basketRepository = basketRepository;
        this.clothesRepository = clothesRepository;
    }

    public Mono<Basket> getElement(Long userId){
        return basketRepository.findByUser(userId);
    }

    public Mono<Basket> postElement(Long userId, Long clothesId){
        List<Clothes> list = new ArrayList<>();
        return basketRepository.findByUser(userId)
                .flatMap(value -> {
                    System.out.println("перед добавлением " + list.size());
                    clothesRepository.findById(clothesId).subscribe(list::add);
                    System.out.println("середина " + list.size());
                    if (!value.getItems().isEmpty() && value.getItems() != null){
                        list.addAll(value.getItems());
                    }
                    System.out.println("после добавления " + list.size());
                    value.setItems(list);
                    return basketRepository.save(value);
                });
    }

}

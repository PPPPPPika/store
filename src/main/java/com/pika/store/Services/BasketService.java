package com.pika.store.Services;

import com.pika.store.Models.Basket;
import com.pika.store.Models.Clothes;
import com.pika.store.Repositorys.BasketRepository;
import com.pika.store.Repositorys.ClothesRepository;
import com.pika.store.Repositorys.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BasketService {
    private final BasketRepository basketRepository;
    private final OrderRepository orderRepository;
    private final ClothesRepository clothesRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository,
                         OrderRepository orderRepository,
                         ClothesRepository clothesRepository) {
        this.basketRepository = basketRepository;
        this.orderRepository = orderRepository;
        this.clothesRepository = clothesRepository;
    }

    public Flux<Basket> getElements(Long userId){
        return basketRepository.findByUser(userId);
    }

    public Mono<Basket> postElement(Long userId, Long clothesId){
        return basketRepository.save(new Basket(null, userId, clothesId));
    }

    public Mono<Void> deleteElement(Long userId, Long clothesId){
        return basketRepository.deleteElement(userId, clothesId);
    }

    public Mono<Void> buyClothes(Long userId, Long[] itemsArray){
        return orderRepository.savePurchase(userId, itemsArray)
                .and(saveElements(decreaseAmount(extractElements(userId))))
                .then(basketRepository.deleteElements(userId));
    }

    private Flux<Clothes> extractElements(Long userId){
        return clothesRepository.findAllById(basketRepository.findByUser(userId).map(Basket::getItems));
    }

    private Flux<Clothes> decreaseAmount(Flux<Clothes> clothesFlux){
        return clothesFlux.map(clothes -> {
            clothes.setAmount(clothes.getAmount() - 1);
            return clothes;
        });
    }

    private Flux<Clothes> saveElements(Flux<Clothes> clothesFlux){
        return clothesRepository.saveAll(clothesFlux);
    }
}

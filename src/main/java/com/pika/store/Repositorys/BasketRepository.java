package com.pika.store.Repositorys;

import com.pika.store.Models.Basket;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BasketRepository extends ReactiveCrudRepository<Basket, Long> {
    @Query("SELECT id, owner, item FROM public.baskets WHERE owner = :id")
    Flux<Basket> findByUser(Long id);

    @Query("DELETE FROM public.baskets WHERE owner = :userId and item = :clothesId")
    Mono<Void> deleteElement(Long userId, Long clothesId);
}

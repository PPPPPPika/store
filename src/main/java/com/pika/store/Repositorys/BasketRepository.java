package com.pika.store.Repositorys;

import com.pika.store.Models.Basket;
import com.pika.store.Models.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BasketRepository extends ReactiveCrudRepository<Basket, Long> {
    Mono<Basket> findByUser(Long id);
}

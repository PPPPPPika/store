package com.pika.store.Repositorys;

import com.pika.store.Models.Order;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
    @Query("INSERT INTO public.order(usr, basket) VALUES (:userId, :itemsArray);")
    Mono<Order> savePurchase(Long userId, Long[] itemsArray);

    @Override
    @Query("SELECT id, usr, basket FROM public.order;")
    Flux<Order> findAll();
}

package com.pika.store.Repositorys;

import com.pika.store.Models.Clothes;
import com.pika.store.Models.Enums.Genus;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ClothesRepository extends ReactiveCrudRepository<Clothes, Long> {
    Flux<Clothes> findByGenus(Genus genus);
}

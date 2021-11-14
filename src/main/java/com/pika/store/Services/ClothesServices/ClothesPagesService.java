package com.pika.store.Services.ClothesServices;

import com.pika.store.Models.Clothes;
import com.pika.store.Models.Enums.Genus;
import com.pika.store.Repositorys.ClothesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClothesPagesService {
    private final ClothesRepository clothesRepository;

    @Autowired
    public ClothesPagesService(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }

    private Genus genusManager(String genus){
        if (genus.equalsIgnoreCase(String.valueOf(Genus.MAN)))
            return Genus.MAN;
        if (genus.equalsIgnoreCase(String.valueOf(Genus.WOMAN)))
            return Genus.WOMAN;
        return null;
    }

    public Flux<Clothes> getAllElements(String genus){
        return clothesRepository.findByGenus(genusManager(genus));
    }

    public Mono<Clothes> getElement(String genus, Long id){
        return clothesRepository.findByGenus(genusManager(genus)).filter(value -> value.getId().equals(id)).next();
    }
}

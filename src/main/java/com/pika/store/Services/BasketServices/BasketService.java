package com.pika.store.Services.BasketServices;

import com.pika.store.Models.Basket;
import com.pika.store.Repositorys.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BasketService {
    private final BasketRepository basketRepository;

    @Autowired
    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
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





















    /*public Mono<Basket> postElement(Long userId, Long clothesId){
        Mono<Clothes> clothesMono = clothesRepository.findById(clothesId);
        Mono<Basket> basketMono = basketRepository.findByUser(userId);

        Mono<String> serStringMono = clothesMono.map(clothes -> clothesGson.getSerializerGson().toJson(clothes).toString());

        Basket basket = new Basket();
        basketMono.subscribe(value -> {
            basket.setId(value.getId());
            basket.setUser(value.getUser());
        });
        serStringMono.subscribe(value -> {
            basket.setItems(value.toString());
        });

        System.out.println("ololosha"+ basket.getId() + basket.getUser() + basket.getItems());

        return basketRepository.save(basket);
    }*/










    /*public Mono<Basket> postElement(Long userId, Long clothesId){
        return basketRepository.findByUser(userId).flatMap(basket -> {
            List<Clothes> itemsList;
            if (basket.getItems() == null || basket.getItems().isEmpty())
                itemsList = new ArrayList<>();

            Type type = new TypeToken<List<Clothes>>(){}.getType();
            itemsList = clothesGson.getDeserializerGson().fromJson(basket.getItems().toString(), type);


            clothesRepository.findById(clothesId).take(Duration.ofMillis(1)).subscribe(itemsList::add);

            String json = clothesGson.getSerializerGson().toJson(itemsList);
            System.out.println(json);

            basket.setItems(json);
            System.out.println("id " + basket.getId());
            System.out.println("user " + basket.getUser());
            System.out.println("items" + basket.getItems());

            return basketRepository.save(basket);
        });
    }*//*


    static Clothes str1 = new Clothes();

    public Mono<Basket> postElement(Long userId, Long clothesId){
        return basketRepository.findByUser(userId).flatMap(basket -> {

            String str = basket.getItems();
            if (str == null)
                str = "";

            ArrayList<Clothes> clothesList = deserializeItems(str);

            getClothes(clothesId);
            Clothes clothes = str1;
            //System.out.println("clothes in post: " + clothes.getId());

            if (clothes != null) {
                clothesList.add(clothes);
                System.out.println("cl not null" + clothes.getId());
            }


            String newItems = serializeItems(clothesList);
            System.out.println("new items in post" + newItems);


            basket.setItems(newItems);

            return basketRepository.save(basket);
        });
    }

    public ArrayList<Clothes> deserializeItems(String items){
        List<Clothes> itemsList;
        if (items == null || items.isEmpty() || items.equals(" ") || items.equals("[]"))
            itemsList = new ArrayList<>();
        else
            itemsList = clothesGson.getDeserializerGson().fromJson(items, new TypeToken<List<Clothes>>(){}.getType());
        *//*1*//*System.out.println(itemsList);*//*1*//*
        return (ArrayList<Clothes>) itemsList;
    }

    public String serializeItems(ArrayList<Clothes> listItems){
        return clothesGson.getSerializerGson().toJson(listItems);
    }

    *//*public Clothes getClothes(Long id){
        AtomicReference<Clothes> clothes = new AtomicReference<>(); //ЧТО БЛЯТЬ ЗА АТОМИК!!!!!!!!!
        clothesRepositoryReactive.findById(id).take(Duration.ofMillis(1)).doOnNext(clothes::set).subscribe(System.out::println);
        return clothes.get();
    }*//*

    public void *//*Clothes*//* getClothes(Long id){
        clothesRepositoryReactive.findById(id).take(Duration.ofMillis(1)).subscribe(clothes -> {
            //str1 = clothes;
            str1 = new Clothes(clothes.getId(), clothes.getName(), clothes.getBrand(), clothes.getSize(),
                               clothes.getAmount(), clothes.getDescription(), clothes.getGenus(), clothes.getType());
        });
        //return ;
    }*/





}

package com.pika.store;

import com.google.gson.reflect.TypeToken;
import com.pika.store.Models.Clothes;
import com.pika.store.Models.Enums.Genus;
import com.pika.store.Models.Enums.TypeClothes;
import com.pika.store.Services.ClothesServices.ClothesSerialization.ClothesGson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class TestMySer {
    public static void main(String[] args) {





        //Gson gsonSer = new ClothesGson().getSerializerGson();
        //Gson gsonDes = new ClothesGson().getDeserializerGson();

        List<Clothes> clothesList = new ArrayList<>(Arrays.asList(new Clothes(1L, "qwe", "asd", "xxx", 1, "pfpfppf", Genus.MAN, TypeClothes.JEANS)));

        String json = new ClothesGson().getSerializerGson().toJson(clothesList);

        clothesList.forEach(System.out::println);

        Type type1 = new TypeToken<List<Clothes>>(){}.getType();
        List<Clothes> read = new ClothesGson().getDeserializerGson().fromJson(json, type1);
        System.out.println(read);




        String items = "[{\"id\":\"1\",\"name\":\"qwe\",\"brand\":\"asd\",\"size\":\"xxx\",\"amount\":\"1\",\"description\":\"pfpfppf\",\"genus\":\"MAN\",\"type\":\"JEANS\"}]";

        /*if (items == null || items.isEmpty() || items.equals(" ")){
            items = "";
        } else {
            new ClothesGson().getDeserializerGson().fromJson(items, new TypeToken<List<Clothes>>(){}.getType());

            List<Clothes> list = new ClothesGson().getDeserializerGson().fromJson(items, new TypeToken<List<Clothes>>(){}.getType());
            System.out.println(list);
        }

        System.out.println(items);*/




        /*List<Clothes> itemsList;
        if (items == null || items.isEmpty() || items.equals(" "))
            itemsList = new ArrayList<>();
        else
            itemsList = new ClothesGson().getDeserializerGson().fromJson(items, new TypeToken<List<Clothes>>(){}.getType());


        System.out.println(itemsList);

        String s = new ClothesGson().getSerializerGson().toJson(itemsList);

        System.out.println(s);*/

        AtomicReference<Clothes> clothes = new AtomicReference<>();
        clothes.set(new Clothes(1L, "qwe", "asd", "xxx", 1, "pfpfppf", Genus.MAN, TypeClothes.JEANS));
        System.out.println(clothes.get());




    }
}

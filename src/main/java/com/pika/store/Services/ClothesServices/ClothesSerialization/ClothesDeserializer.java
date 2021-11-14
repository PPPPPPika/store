package com.pika.store.Services.ClothesServices.ClothesSerialization;

import com.google.gson.*;
import com.pika.store.Models.Clothes;
import com.pika.store.Models.Enums.Genus;
import com.pika.store.Models.Enums.TypeClothes;

import java.lang.reflect.Type;

public class ClothesDeserializer implements JsonDeserializer<Clothes> {
    @Override
    public Clothes deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Long id = object.get("id").getAsLong();
        String name = object.get("name").getAsString();
        String brand = object.get("brand").getAsString();
        String size = object.get("size").getAsString();
        Integer amount = object.get("amount").getAsInt();
        String description = object.get("description").getAsString();
        Genus genus = determinantGenus(object.get("genus").getAsString());
        TypeClothes typeClothes = determinantTypeClothes(object.get("type").getAsString());
        return new Clothes(id, name, brand, size, amount, description, genus, typeClothes);
    }

    //////////////////////////////////////////вынести в отдельный класс если снова понадобиться
    private Genus determinantGenus(String genus){
        return switch (genus) {
            case "MAN" -> Genus.MAN;
            case "WOMAN" -> Genus.WOMAN;
            default -> throw new IllegalStateException("Unexpected value(ClothesDeserializer): " + genus);
        };
    }

    private TypeClothes determinantTypeClothes(String typeClothes){
        return switch (typeClothes){
            case "JACKETS" -> TypeClothes.JACKETS;
            case "SWEATERS" -> TypeClothes.SWEATERS;
            case "T_SHIRTS" -> TypeClothes.T_SHIRTS;
            case "JEANS" -> TypeClothes.JEANS;
            case "SHOES" -> TypeClothes.SHOES;
            default -> throw new IllegalStateException("Unexpected value(ClothesDeserializer): " + typeClothes);
        };
    }
    ///////////////////////////////////////
}

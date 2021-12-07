package com.pika.store.Services.ClothesServices.ClothesSerialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pika.store.Models.Clothes;

import java.lang.reflect.Type;

public class ClothesSerializer implements JsonSerializer<Clothes> {
    @Override
    public JsonElement serialize(Clothes clothes, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("id", clothes.getId().toString());
        object.addProperty("name", clothes.getName().toString());
        object.addProperty("brand", clothes.getBrand().toString());
        object.addProperty("size", clothes.getSize().toString());
        object.addProperty("amount", clothes.getAmount().toString());
        object.addProperty("description", clothes.getDescription().toString());
        object.addProperty("genus", clothes.getGenus().toString());
        object.addProperty("type", clothes.getType().toString());
        return object;
    }

}

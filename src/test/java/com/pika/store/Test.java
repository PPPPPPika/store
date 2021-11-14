package com.pika.store;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.*;

public class Test {
    public static void main(String[] args) {

        /*Flux<Clothes> flux = Flux.just(
                new Clothes(2L, "fg5-hw45", "nike", "m", "gsfdgdfsg", Genus.MAN, TypeClothes.JACKETS),
                new Clothes(3L, "66zxc", "ad", "x", "bvcbxcvb", Genus.WOMAN, TypeClothes.JEANS),
                new Clothes(4L, "xcdeeeee-vxcv", "ad", "s", "vcvcvcvcvc", Genus.MAN, TypeClothes.SHOES)
        );

        Long num = 3L;

        Mono<Clothes> clothesMono = flux.filter(value -> value.getBrand().equals("ad")).next();
        clothesMono.subscribe(System.out::println);
        System.out.println();*/

        /*List<String> list = new ArrayList<>();
        System.out.println(list.isEmpty());*/

        class A {
            public A(String str, int num) {
                this.str = str;
                this.num = num;
            }

            public String str;
            public int num;
        }

        List<String> list = new ArrayList<>(Arrays.asList("qwe", "sdf", "czxczxcsdcs", "fwegferg"));
        List<String> list1 = new ArrayList<>(Arrays.asList("qwe"));
        List<String> list2 = new ArrayList<>(Arrays.asList("qwe", "rty"));
        List<String> list3 = new ArrayList<>(Arrays.asList("qwe", "rty"));
        List<A> list4 = new ArrayList<>(Arrays.asList(new A("q", 5), new A("cz", 2) ));
        List<A> list5 = new ArrayList<>(Arrays.asList(new A("q", 5), new A("cz", 2) ));

        A q1 = new A("q", 1);
        A q2 = new A("q", 1);

        System.out.println(q1.hashCode() + " | " + q2.hashCode());
        System.out.println(q1.hashCode() == q2.hashCode());

        System.out.println(list.hashCode());
        System.out.println(list1.hashCode());
        System.out.println(list.hashCode() == list1.hashCode());

        System.out.println(list2.hashCode());
        System.out.println(list3.hashCode());
        System.out.println(list4.hashCode());
        System.out.println(list5.hashCode());
/*

        //сериализация в json
        //писать результат сериализации будем во Writer(StringWriter)
        StringWriter writer = new StringWriter();
        //это объект Jackson, который выполняет сериализацию
        ObjectMapper mapper = new ObjectMapper();
        // сама сериализация: 1-куда, 2-что
        try {
            mapper.writeValue(writer, list4);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(writer.toString());

        String s = writer.toString();

        //десериализация из json
        StringReader stringReader = new StringReader(s);
        ObjectMapper mapper1 = new ObjectMapper();

        List<a> aList = new ArrayList<>();
        try {
            List<Object> list6 = mapper1.readValue(stringReader, List.class);
            for (Object object : list6){
                aList.add((a) object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(aList);
*/

        A a1 = new A("q", 1);
        A a2 = new A("w", 2);
        //List<A> list4 =
        //new ArrayList<>(Arrays.asList(new A("q", 5),
        //                              new A("cz", 2)));

        /*Gson gson = new Gson();
        *//*gson.toJson(1);//gson.fromJson()
        System.out.println(gson);*//*

        for (A a : list4){
            System.out.println(a.num);
            System.out.println(a.str);
        }

        String json = gson.toJson(list4);

        Type type = new TypeToken<List<A>>(){}.getType();
        List<A> aList = gson.fromJson(json, type);

        for (A a : aList){
            System.out.println(a.num);
            System.out.println(a.str);
        }*/

        /*Gson gson = new Gson();
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("USD", 123);
        map.put("EUR", 321);
        String json = gson.toJson(map);
        Type type = new TypeToken<Map<String, Integer>>(){}.getType();
        Map<String, Integer> read = gson.fromJson(json, type);
        System.out.println(read);*/

        /*class ClsConverter implements JsonSerializer<Cls>, JsonDeserializer<Cls> {
            @Override
            public JsonElement serialize(Cls cls, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject object = new JsonObject();
                object.addProperty("integer", cls.getInteger().toString());
                object.addProperty("string", cls.getString().toString());
                return object;
            }

            @Override
            public Cls deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject object = jsonElement.getAsJsonObject();
                Integer integer = object.get("integer").getAsInt();
                String string = object.get("string").getAsString();
                return new Cls(integer, string);
            }
        }*/


        class ClsSer implements JsonSerializer<Cls>{
            @Override
            public JsonElement serialize(Cls cls, Type type, JsonSerializationContext jsonSerializationContext) {
                JsonObject object = new JsonObject();
                object.addProperty("integer", cls.getInteger().toString());
                object.addProperty("string", cls.getString().toString());
                return object;
            }
        }

        class ClsDes implements JsonDeserializer<Cls> {
            @Override
            public Cls deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                JsonObject object = jsonElement.getAsJsonObject();
                Integer integer = object.get("integer").getAsInt();
                String string = object.get("string").getAsString();
                return new Cls(integer, string);
            }
        }




        GsonBuilder builderSer = new GsonBuilder();
        GsonBuilder builderDes = new GsonBuilder();
        builderSer.registerTypeAdapter(Cls.class, new ClsSer());
        builderDes.registerTypeAdapter(Cls.class, new ClsDes());
        Gson gsonSer = builderSer.create();
        Gson gsonDes = builderDes.create();

        List<Cls> clsList = new ArrayList<>(Arrays.asList(new Cls(1, "q"),
                                                          new Cls(2, "w")));
        String json = gsonSer.toJson(clsList);
        System.out.println(json);
        Type type = new TypeToken<List<Cls>>(){}.getType();
        List<Cls> read = gsonDes.fromJson(json, type);
        System.out.println(read);

        for (Cls cls : read){
            Integer integer = cls.integer;
            System.out.println("int " + integer);
            System.out.println(cls.integer);
            System.out.println(cls.string);
        }

        /*Gson gson = new Gson();
        String json = gson.toJson(new Cls(1, "q"));
        System.out.println(json.toString());

        Cls cls1 = gson.fromJson(json, Cls.class);

        System.out.println(cls1);*/

    }
}

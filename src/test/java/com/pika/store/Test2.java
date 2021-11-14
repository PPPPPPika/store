package com.pika.store;

import com.pika.store.Models.Enums.Genus;

public class Test2 {
    public static void main(String[] args) {
        /*System.out.println(Genus.MAN);
        System.out.println(Genus.MAN.name().equals("MAN"));*/

        String genus = "WOMAN";
        System.out.println(determinantGenus(genus));


    }

    private static Genus determinantGenus(String genus){
        return switch (genus) {
            case "MAN" -> Genus.MAN;
            case "WOMAN" -> Genus.WOMAN;
            default -> null;
        };
    }

}

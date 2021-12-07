package com.pika.store;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

public class TP {


    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        String str = "$2a$12$afhNEA5d.bYygc8XPojJKuKWi3ItMueSsSGgae5s99t5fFlYGkRM2";


        System.out.println(new BCryptPasswordEncoder(12).matches("123", str));
    }



}

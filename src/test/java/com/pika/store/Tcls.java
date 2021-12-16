package com.pika.store;

public class Tcls {
    public static void main(String[] args) {
        String s1 = "";
        String s2 = " ";
        String s3 = "12 vsdf vadsf 453";
        String s4 = "  ";

        /*System.out.println(s1.isEmpty());
        System.out.println(s2.isEmpty());
        System.out.println(s3.isEmpty());
        System.out.println(s4.isEmpty());*/

        /*if (!s1.matches(".*\\w.*")){
            System.out.println(s1 + "only enter");
        }
        if (!s2.matches(".*\\w.*")){
            System.out.println(s2 + "only enter");
        }
        if (!s3.matches(".*\\w.*")){
            System.out.println(s3 + "only enter");
        }
        if (!s4.matches(".*\\w.*")){
            System.out.println(s4 + "only enter");
        }*/

        if (!s1.isBlank()){
            System.out.println(s1 + "only enter");
        }
        if (!s2.isBlank()){
            System.out.println(s2 + "only enter");
        }
        if (!s3.isBlank()){
            System.out.println(s3 + "only enter");
        }
        if (!s4.isBlank()){
            System.out.println(s4 + "only enter");
        }


    }
}

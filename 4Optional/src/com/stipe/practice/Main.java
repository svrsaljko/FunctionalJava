package com.stipe.practice;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        String string = "optional";
        Optional<String> optional = Optional.of(string);

        //empty optional
        Optional<Integer> emptyOptional = Optional.empty();

        // nullable
        Optional<String> emptyOptional2 = Optional.ofNullable(null);
        Optional<String> nullable = Optional.ofNullable(string);

        // get method --> not recommended !!
        String gettedOptionalString = optional.get();
        System.out.println("string: " + gettedOptionalString);

        //System.out.println(emptyOptional2.get()); NoSuchElementException, no value present !

        // isPresent
        System.out.println(nullable.isPresent() ? nullable.get() : "empty");
        System.out.println(emptyOptional.isPresent() ? emptyOptional.get() : "empty");

        

    }
}

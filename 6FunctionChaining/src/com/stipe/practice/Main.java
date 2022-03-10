package com.stipe.practice;

import java.util.function.Function;

public class Main {
    public static void main(String[] args)
    {
        Consumer<String> c1 = s -> System.out.println("c1 this: " + s);
        //Consumer<String> c2 = System.out::println;

        //iz debugiraj!!

        //Consumer<String> c1 = (s) -> System.out.println(s.substring(0));
        //Consumer<String> c2 = (s) -> System.out.println(s.substring(s.length()/2-1));

        //Consumer<String> c3 = s -> {
         //   c1.accept(s.substring(0,s.length()/2));
         //  c2.accept(s.substring(s.length()/2));
        //};

        // primjer koji nedaje dojam za chaining
        //c3.accept("abcdef");

        Consumer<String> c5 = s -> System.out.println("c5 next: " + s);

        // primjer koji daje dojam za chaining
        Consumer<String> c4 = c1.thenAccept(c5);
        c4.accept("Twice");

        try {
            c4 = c1.thenAccept(null);
        } catch (NullPointerException ex) {
            System.out.println("Null pointer exception");
        }

        Function<Integer,Integer> f1 = s -> s + 2;
        Function<Integer,Integer> f2 = s -> s * 2;
        Function<Integer,Integer> f3 = f1.andThen(f2);
        // prvo se izvrsi f1 pa f2
        System.out.println("Ukupno: " + f3.apply(10));

    }
}

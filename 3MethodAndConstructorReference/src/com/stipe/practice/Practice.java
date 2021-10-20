package com.stipe.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Practice {
    public void run() {
        // existing object instance method
        //methodReferenceConsumer();
        // class static method
        //methodReferenceSupplier();
        //methodReferenceBiFunction();
        // Class instance method
       // classInstanceMethod();
        // constructor reference
        constructorReference();
    }

    void constructorReference() {
        //Function <Runnable, Thread> threadGenerator = r-> new Thread(r);
        Function <Runnable, Thread> threadGenerator = Thread::new;
        Runnable task1 = () -> System.out.println("Task 1 executed");
        Runnable task2 = () -> System.out.println("Task 2 executed");

        Thread thread1 = threadGenerator.apply(task1);
        Thread thread2 = threadGenerator.apply(task2);

        thread1.start();
        thread2.start();

        // more functional looking code example

        threadGenerator
                .apply(()-> System.out.println("Task 3 executed"))
                .start();
    }

    void classInstanceMethod() {
        List<String> list = List.of("Stipe","Kit","","Kat","Pauza");
        Function<String,Integer> function = String::length;
        int index =0;
        for( String e: list) {
            System.out.println("Duljina " + index + ". elementa liste: " + function.apply(list.get(index)));
            index++;
        }
    }

     void methodReferenceBiFunction() {
         BiFunction<String,String,String> function = SumString::method;
         System.out.println(function.apply("Stipe","Pipe"));
    }

    static class SumString {
        static String method(String a,String b) {
            return a + "." + b;
        }
    }

     void methodReferenceSupplier() {
        // nema lambde vise opet method reference
        Supplier<Double> supplier = Math::random;
        System.out.println(supplier.get()*100);
    }

     void methodReferenceConsumer() {
        List<Integer> list = List.of(12,22,55,77);
        //Consumer consumer = e -> System.out.println("number: " + e);
        //method reference nemora biti lambda :: prije metode !!!
        Consumer consumer = System.out::println;
        printNumbers(list,consumer);
    }

     static <T> void printNumbers(List<T> list, Consumer<T> consumer) {
        for(T e:list) {
            consumer.accept(e);
        }
    }

}

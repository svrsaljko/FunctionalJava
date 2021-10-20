package com.stipe.practice;

import com.stipe.practice.interfaces.FunctionGenerics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.IntStream;

public class Practice {

    public void run() {
        //funGenerics();
        //predicate();
        //consumer();
        //supplier();
        //function();
        //unaryOperator();
        //biFunction();
        binaryOperator();
    }

    //Bifunction sa istim tipom
    private static void binaryOperator() {
        BinaryOperator<String> operator = (a,b) -> a + "." + b;
        System.out.println(operator.apply("Stipe","Pipe"));
    }

    private static void biFunction() {
        BiFunction<String,String,String> biFunction = (a,b) -> a+b;
        System.out.println(biFunction.apply("Stipe ","Pipe"));
        BiFunction<String,String,Integer> biFunctionLength = (a,b) -> (a+b).length();
        System.out.println(biFunctionLength.apply("Stipe ","Pipe"));
    }

    private static void unaryOperator() {
        List<Integer> list = new ArrayList<>();
        IntStream.rangeClosed(1,10).forEach(
                consumedNumber -> list.add(consumedNumber)
        );
        UnaryOperator<Integer> unaryOperatorMultiply = e -> e*100;
        System.out.println("multiplied numbers: " + mapper(list,unaryOperatorMultiply));
    }

    private static <T> List<T> mapper(List<T> list, UnaryOperator<T> unaryOperator) {
        List<T> newList = new ArrayList<>();
        for( T e:list ) {
         newList.add(unaryOperator.apply(e));
        }
        return newList;
    }

    private static void function() {
        List<String> list = List.of("stipe","aaa","pipe","","aaa");
        Function<String,Integer> function = s -> s.length();
        System.out.println("result: " + calculateLength(list,function));
    }

    private static <T,R> List<R> calculateLength( List<T> list, Function<T,R> function ) {
        List<R> newList = new ArrayList<>();
        for(T e: list) {
            newList.add(function.apply(e));
        }
        return newList;
    }

    private void supplier() {
        Supplier<String> supplier = () -> new String("Stipe novi string");
        System.out.println("Supplier: " + supplier.get());

        Supplier<Integer> randomNumber = () -> Math.toIntExact(Math.round(Math.random() * 100));
        System.out.println("Random number: " + randomNumber.get());
    }

    private void consumer() {
        List<Integer> list = List.of(1,3,21,12,66,555,4);
        Consumer<Integer> consumer = e -> System.out.println("element: " + e);
        printList(list, consumer);

    }

    private static <T> void printList( List<T> list, Consumer<T> consumer ) {
        for(T element: list) {
            consumer.accept(element);
        }
    }

    private void funGenerics() {

        FunctionGenerics<String,String> fun = s -> s.substring(1,5);
        String r = fun.execute("stipee");
        System.out.println("string je: " + r);

        FunctionGenerics <String,Integer> fun1 = s -> s.length();
        int i = fun1.execute("Stipee");
        System.out.println("duljina stringa je: " + i);

    }

    private void predicate() {
        List<String> list = new ArrayList<>();
        list.add("Stipe");
        list.add("");
        list.add("Pipe");
        list.add("");
        list.add("StipePipe");

        Predicate<String> predicate = s -> !s.isEmpty();
        List<String> newList = filterList(list,predicate);
        System.out.println("list with elements: " + newList );
        Predicate<String> containsPipe = s -> s.contains("Pipe");
        newList = filterList(newList, containsPipe);
        System.out.println("contains Pipe: " + newList);

        Predicate<String> lengthFilter = s -> s.length() > 5;
        newList = filterList(newList, lengthFilter);
        System.out.println("new string with length filter > 5: " + newList);

        List<Integer> integerList = new ArrayList<>();
        // alternative !!
        List<Integer> intList = List.of(1,2,3,4,5,6,7,8,9,10);
        IntStream.range(1,100).forEach( i -> integerList.add(i));
        Predicate<Integer> isEvenNumber = i -> i%2 ==0;
        List<Integer> evenNumbers = filterList(intList,isEvenNumber);
        System.out.println("even numbers: "+ evenNumbers);
    }

    //generics
    private static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        List<T> newList = new ArrayList<>();
        for(T listElement:list) {
            if(predicate.test(listElement)) {
                newList.add(listElement);
            }
        }
        return newList;
    }
    // string
/*    private static List<String> filterList(List<String> list, Predicate predicate) {
        List<String> newList = new ArrayList<>();
        for(String string:list) {
           if(predicate.test(string)) {
               newList.add(string);
           }
        }
        return newList;
    }*/

}

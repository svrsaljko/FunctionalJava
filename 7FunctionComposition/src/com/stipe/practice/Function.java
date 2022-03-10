package com.stipe.practice;

@FunctionalInterface
public interface Function<T,R> {
   R apply(T t);

   // tip <V> nije potrebno stavljati u definiciju interface-a, nego samo u funkciju

   default <V> Function<V,R> compose(Function<V, T> before) {
      return (V v) -> {
         //System.out.println("before je tipa: " + before.getClass() );
         //System.out.println("vrijednost before-a : " + before.toString());

         //square
         //System.out.println("v je tipa: " + v.getClass() );
         //System.out.println("vrijednost v-a : " + v.toString());

         return apply(before.apply(v));
      };
   }
}

package com.stipe.practice;

public class Main {
    public static void main(String[] args) {

        Function<Square,Integer> fun1 = s -> s.getArea();
        Function<Integer,Double> fun2 = area -> Math.sqrt(area);

        Function<Double,Double> weirdFun = s -> 5.05 + s;
        //Function<Square, Double> getSide = fun2.compose(fun1);

        // ili
        Function<Square, Double> getSide = (s) -> fun2.apply(fun1.apply(s));

        Function<Square, Double> getWeirdSide = (s) -> weirdFun.apply(getSide.apply(s));

        Square s = new Square();
        s.setArea(50);

        Double side = getSide.apply(s);

        System.out.println("Side: " + side);

        Double weirdSide = getWeirdSide.apply(s);

        System.out.println("weirdSide: " + weirdSide);
    }
}

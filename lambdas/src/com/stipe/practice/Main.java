package com.stipe.practice;

import com.stipe.practice.funinterfaces.LengthOfString;
import com.stipe.practice.funinterfaces.MathOperation;
import com.stipe.practice.funinterfaces.Name;

public class Main {

    public static void main(String[] args) {

        // example with public void method
        Name name = () -> System.out.println("Stipe");
        name.myName();

        // example with arguments

        MathOperation summary = (a,b) -> System.out.println("Sum:  " + (a + b));
        MathOperation substraction = (a,b) -> System.out.println("Sub:  " + (a - b));

        summary.operation(10,5);
        substraction.operation(10,5);

        // example with return type

        LengthOfString los = (a) -> a.length();

        System.out.println("duljina stringa je: " + los.length("stipe"));

        //LengthOfString bzvzVrati10 = (a) -> 10;
        //System.out.println("bzvz vrati 10:  " + bzvzVrati10.length("a"));
        // multiline lambda example
        LengthOfString los2 = (a) -> {
          int length = 0;
          length = a.length();
          System.out.println("length of string is: " + length);
          return  length;
        };

        los2.length("stipe-pipe");
     }
}

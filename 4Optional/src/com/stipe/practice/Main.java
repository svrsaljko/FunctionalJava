package com.stipe.practice;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        //introduction();
        operations();
    }

    static void operations() {
        Optional<String> optional = Optional.of("Value");

        // map
        Optional<String> map = optional.map(val -> "Replaced");
        System.out.println(map.get());

        // map on EmptyOptional
        Optional<String> emptyOptional = Optional.empty();
        Function<String,String> emptyOptionalFun = val -> "Replaced";
        String orElse = emptyOptional.map(emptyOptionalFun).orElse("Empty");
        System.out.println("orElse: " + orElse);
    }

    static void introduction() {
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

        Optional<Integer> num = Optional.of(10);
        Optional<Integer> noNum = Optional.empty();
        // orElse vs orElseGet https://www.baeldung.com/java-optional-or-else-vs-or-else-get
        // orElseGet bolje performanse
        //orElse -> kreira opcionalnu vrijednost ako mu i ne triba
        System.out.println("num " + num.orElse(0));
        System.out.println("noNum " + noNum.orElse(0));

        Supplier<Integer> supplier = () -> 11;

        // orElseGet -> ne kreira nista samo zove suppliera ako nema nista drugo, inace ga nezove
        Integer orElseGet = noNum.orElseGet(supplier);
        System.out.println("orElseGet: " + orElseGet);

        //orElseThrow ako nema vrijednosti baci Exception (ne zelimo nikakvu drugu vrijednost nego bacit Exception)
        try {
            Supplier<IllegalArgumentException> exceptionSupplier = () -> new IllegalArgumentException();
            noNum.orElseThrow(exceptionSupplier);
        } catch (IllegalArgumentException ex) {
            // exception uhvacen i handlean doli mi se ne crveni :)
            System.out.println("Exception catched");
        }
    }

}

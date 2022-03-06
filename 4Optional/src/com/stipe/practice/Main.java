package com.stipe.practice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        //introduction();
        //operations();
        //moreOperations();
        ifPresentOrElse();
    }

    private static void ifPresentOrElse() {
        // ifPresentOrElse
        //consumer i runnable
        Optional.empty().ifPresentOrElse(System.out::println,() -> System.out.println("Value is absent"));

        // stream
        Optional<String> optionalString = Optional.of("optionalString");
        Stream<String> streamString = optionalString.stream();
        streamString.forEach(System.out::println);
        // nista se nedegodi
        Optional.empty().stream().forEach(System.out::println);

        // or
        optionalString.or(()-> Optional.of("new value")).ifPresent(System.out::println);
        // ako je prazan ispise ovaj novi
        Optional.empty().or(()-> Optional.of("new value")).ifPresent(System.out::println);

        // ako je supplier null, onda nam vraca null pointer exception
        try {
            Optional.empty().or(() -> null).ifPresent(System.out::println);
        } catch (Exception ex) {
            System.out.println("NullPointer Exception, supplier je null");
        }

        // equals

        System.out.println(optionalString.equals(Optional.of("optionalString")));
        //true
        System.out.println(Optional.empty().equals(Optional.empty()));
        //true
        System.out.println(optionalString.equals(Optional.of("optionalStrin")));
        // false

        //hashCode
        System.out.println("optional string hash code " + optionalString.hashCode());
        System.out.println("optional empty hash code " + Optional.empty().hashCode());
        // 0
    }
    private static void moreOperations() {
        // ifPresent
        Optional<String> optionalString = Optional.of("Value");
        // if present izvrsava consumera iz vrijednosti o optionalu
        optionalString.ifPresent(System.out::println);

        // ili consumer definiran
        Consumer<Optional<String>> consumer = val -> System.out.println("drugi nacin: " + (val.isPresent() ?val.get() :"empty"));
        consumer.accept(optionalString);
        Optional<String> emptyOptional = Optional.empty();
        consumer.accept(emptyOptional);

        // bolji drugi nacin
        optionalString.ifPresent(val -> System.out.println("jos bolji naicn: " + val ));
        // consumer 2
        Consumer<String> consumer2 = val -> System.out.println("evo ga: " + val);
        optionalString.ifPresent(consumer2);

        // ako je optional prazan nista se ne desava, nema exceptiona
        Optional.empty().ifPresent(System.out::println);
    }

    private static void operations() {
        List<Optional<String>> optionals = Arrays.asList(Optional.of("value1"),Optional.empty(),Optional.of("value2"));
        // map
        System.out.println("optional befofe: ");

        //optionals.stream().forEach(val -> System.out.println("is empty: " + val.isEmpty()));
        //optionals.stream().forEach(val -> System.out.println("is present: " + val.isPresent()));

        List<String> optionalsAfter = optionals.stream().map((val) -> !val.isEmpty() ? "newValue " + val.get() : "empty").collect(Collectors.toList());
        //optionals.stream().forEach(val -> System.out.println(val.get()));
        optionalsAfter.forEach(val -> System.out.println("val: "+ val));
        //System.out.println("optional after: " + mapString.get());

        // map on EmptyOptional
        Optional<String> emptyOptional = Optional.empty();
        Function<String,String> emptyOptionalFun = val -> "Replaced";
        try
        {
            emptyOptional.map(emptyOptionalFun);
            System.out.println("empty Optional " + emptyOptional.get());

        } catch(NoSuchElementException ex) {
            System.out.println("No such element exception occured");
        }
        String orElse = emptyOptional.map(emptyOptionalFun).orElse("Empty");
        System.out.println("orElse: " + orElse);

        // filter
        //List<Optional<String>> filterStringOptionals = optionals
        //optionals = optionals.stream().filter(val -> val.isPresent() && val.get().contains("1") ).collect(Collectors.toList());
        //System.out.println("optionals after filter");
        //optionals.stream().forEach(val -> System.out.println(val.get()));

        // flat map

        // flat map nepotreban
         optionals = optionals.stream().flatMap(Stream::of).filter(val -> val.isPresent() && val.get().contains("2")).collect(Collectors.toList());
        System.out.println("optionals after flatMap");
        optionals.stream().forEach(val -> System.out.println(val.get()));

        // flat map jednostavan primjer

        Optional<String> stringic = Optional.of("Stringic");
        Optional<String> flatMap = stringic.flatMap(val -> Optional.of("Replaced by flat map"));
        System.out.println("flatMap: " + flatMap.get() );

    }

    private static void introduction() {
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
        // orElseGet bolje performanse, zovne suppliera i kreira se objekt samo ako mu triba
        //orElse -> kreira defaultnu opcionalnu vrijednost ako mu i ne triba
        System.out.println("num " + num.orElse(0));
        System.out.println("noNum " + noNum.orElse(0));

        Supplier<Integer> supplier = () -> 11;

        // orElseGet -> ne kreira nista samo zove suppliera ako nema nista drugo, inace ga nezove
        Integer orElseGet = noNum.orElseGet(supplier);
        System.out.println("orElseGet: " + orElseGet);

        //orElseThrow ako nema vrijednosti baci Exception (ne zelimo nikakvu drugu vrijednost nego bacit Exception)
        // zove exception supplier
        try {
            Supplier<IllegalArgumentException> exceptionSupplier = () -> new IllegalArgumentException();
            noNum.orElseThrow(exceptionSupplier);
            // od jave 10 moze i orElseThrow() pa baci no such element exception i bez suppliera
        } catch (IllegalArgumentException ex) {
            // exception uhvacen i handlean doli mi se ne crveni :)
            System.out.println("Exception catched");
        }

        try {
            noNum.orElseThrow();
        }catch (NoSuchElementException ex) {
            //ex.printStackTrace(); crveni se ako se odkomentira
            System.out.println("uhvacen NoSuchElement Exception");
        }
        }

}

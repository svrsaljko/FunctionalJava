package com.stipe.practice;

public class HigherOrderFunctions {


    public static void main(String args[]) {
        //IFactory<Integer> createFactory = createFactory(()-> Math.random()*100, r -> r.intValue());
        IProducer<Double> producer = () -> Math.random()*100;
        //IConfigurator<Double,Integer> configurator = (Double randomNumber) -> randomNumber.intValue();
        IConfigurator<Double,Integer> configurator = Double::intValue;
        IFactory<Integer> createFactory = createFactory(producer,configurator);

        Integer product = createFactory.create();
        System.out.println("product: " + product);

        IProducer<Integer> intProducer = () -> producer.produce().intValue();
        IConfigurator<Integer,Double> doubleConfigurator = i -> i + 0.5;

        IFactory<Double> doubleFactory = createFactory(intProducer,doubleConfigurator);
        double doubleProduct = doubleFactory.create();
        System.out.println("double product: " + doubleProduct);
    }

    public static <T,R> IFactory<R> createFactory(IProducer<T> producer, IConfigurator<T,R> configurator) {

        return () -> {
            T product = producer.produce();
            return configurator.configure(product);
        };
    }



}

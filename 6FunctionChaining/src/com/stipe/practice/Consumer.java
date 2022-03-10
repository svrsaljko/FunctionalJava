package com.stipe.practice;

import java.util.Objects;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);

    default Consumer<T> thenAccept(Consumer<T> next) {
        // potrebno za null pointer exception
        Objects.requireNonNull(next);

        return (T t) -> {
            System.out.println("then Accept: start");
            this.accept(t);
            next.accept(t);
            System.out.println("then Accept: end");
        };
    };
}

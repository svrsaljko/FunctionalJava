package com.stipe.practice.interfaces;

@FunctionalInterface
public interface FunctionGenerics<T,R> {
    R execute(T t);
}

package com.stipe.practice;

@FunctionalInterface
public interface IConfigurator<T,R> {
    R configure(T t);
}

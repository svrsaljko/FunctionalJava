package com.stipe.practice;

@FunctionalInterface
public interface IFactory<T> {
    T create();
}

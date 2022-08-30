package com.nixalevel.generics;

public interface Aggregator<A, T> {
    A agregate(T[] items);
}

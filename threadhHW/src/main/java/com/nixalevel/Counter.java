package com.nixalevel;

public class Counter {

    private volatile int value = 0;

    public synchronized int next() {
        return value++;
    }

}

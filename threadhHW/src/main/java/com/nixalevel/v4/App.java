package com.nixalevel.v4;

public class App {
    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        Producer p = new Producer(buffer);
        Consumer c = new Consumer(buffer);
        p.start();
        c.start();
    }
}

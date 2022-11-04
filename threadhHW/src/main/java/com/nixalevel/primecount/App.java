package com.nixalevel.primecount;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Checker checker = new Checker();
        new Producer(checker);
        new Consumer(checker);
    }

}

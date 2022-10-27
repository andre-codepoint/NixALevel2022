package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class App {
    static AtomicInteger counter = new AtomicInteger(0);
    static volatile AtomicInteger number = new AtomicInteger(0);
    private static Thread t1;
    private static Thread t2;

    static boolean isCheck = false;

    public static void main(String[] args) throws InterruptedException {
        GenerateNunber generateNunber = new GenerateNunber();
        t1 = new Thread(generateNunber::generate);
        t1.start();
        while (counter.get() < 10) {
            t2 = new Thread(() -> {
                {
                    number.set(generateNunber.generate().get());
                }
            });
            t2.start();
            t1.join();
            synchronized (t1) {if (generateNunber.isPrime(number)) {
                counter.set(counter.get() + 1);
                System.out.println(counter.get() + " " + number);
            }}
        }
    }
}

package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GenerateNunber  {
    private boolean ready;
    private AtomicInteger number;
    public  AtomicInteger  generate() {
        {
        Random random = new Random();
        return new AtomicInteger(random.nextInt(100));
        }
    }

    public AtomicInteger getNumber() {
        return number;
    }

    private boolean isPrime;

   public   boolean isPrime(AtomicInteger number) {
        boolean result=false;
        {
        if (number.get() <= 1) {
            return false;
        }
        if (number.get() == 2 || number.get() == 3) {
            return true;
        }
        if (number.get() % 2 == 0) {
            return false;
        }
        int sqrt = (int) Math.sqrt(number.get()) + 1;
        for (int i = 3; i < sqrt; i += 2) {
            if (number.get() % i == 0) {
                return false;
            }
        }
        }
        return result;
}}

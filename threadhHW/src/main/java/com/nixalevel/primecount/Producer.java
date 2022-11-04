package com.nixalevel.primecount;

import java.util.Random;

public class Producer implements Runnable{
    Checker checker;
    private static final int PRIME_NUMBERS = 10;
    private int countSimpleNumber;

    public Producer(Checker checkerIn) {
        this.checker = checkerIn;
        new Thread(this, "Producer").start();
    }

    @Override
    public void run() {
        int randomIntNumber;
        while (countSimpleNumber < PRIME_NUMBERS) {
            randomIntNumber = new Random().nextInt(60);
            checker.putRandomIntNumber(randomIntNumber);
            this.countSimpleNumber = checker.getCountSimpleNumber();
        }
    }

}

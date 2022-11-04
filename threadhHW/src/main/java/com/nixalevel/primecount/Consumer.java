package com.nixalevel.primecount;

public class Consumer implements Runnable{

    Checker checker;
    private static final int NUMBER_OF_TIMES = 10;
    private int countSimpleNumber;

    public Consumer(Checker checkerIn) {
        this.checker = checkerIn;
        new Thread(this, "Consumer").start();
    }

    @Override
    public void run() {
        while (countSimpleNumber < NUMBER_OF_TIMES) {
            checker.checkRandomNumber();
            this.countSimpleNumber = checker.getCountSimpleNumber();
        }
    }
}

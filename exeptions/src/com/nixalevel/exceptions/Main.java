package com.nixalevel.exceptions;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        Retry retry = new Retry(new Block() {
            @Override
            public void run() throws Exception {
                Random rnd = new Random();
                boolean b = rnd.nextBoolean();
                if (b) {
                    throw new Exception("Run was Fault! Throw Exception!");
                }
            }
        }, 10);
        retry.runBlock();
    }
}

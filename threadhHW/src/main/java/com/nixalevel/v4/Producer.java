package com.nixalevel.v4;

import java.util.Random;

public class Producer extends Thread{
    private Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        Random rand = new Random();
        while (true) {
            int n = (rand.nextInt(10000));
            buffer.produce(n);
        }
    }
}

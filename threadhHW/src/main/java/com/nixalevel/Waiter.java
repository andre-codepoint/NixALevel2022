package com.nixalevel;

class Waiter implements Runnable {

    private final Message msg;

    Waiter(Message m) {
        this.msg = m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            try {
                System.out.println(name + " waiting to get notified at time: " + System.currentTimeMillis());
                msg.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
            System.out.println(name + " got notified at time: " + System.currentTimeMillis());
            //process the message now
            System.out.println(name + " processed: " + msg.getMessage());
        }
    }
}

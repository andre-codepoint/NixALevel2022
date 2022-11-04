package com.nixalevel;

public class MutexBlock {

    public static void main(String[] args) {
        Message msg = new Message();
        Waiter waiter1 = new Waiter(msg);
        Waiter waiter2 = new Waiter(msg);
        Notifier notifier = new Notifier(msg);

        new Thread(waiter1,"waiter1").start();
        new Thread(waiter2, "waiter2").start();
        new Thread(notifier, "notifier").start();
    }

}

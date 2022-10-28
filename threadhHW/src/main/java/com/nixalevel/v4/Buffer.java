package com.nixalevel.v4;

public class Buffer {
    private int data;
    private boolean empty;
    private int count;

    public  boolean isPrime(int inputNumber)
    {
        boolean isItPrime = true;

        if(inputNumber <= 1)
        {
            isItPrime = false;

            return isItPrime;
        }
        else
        {
            for (int i = 2; i<= inputNumber/2; i++)
            {
                if ((inputNumber % i) == 0)
                {
                    isItPrime = false;

                    break;
                }
            }

            return isItPrime;
        }
    }
    public Buffer() {
        this.empty = true;
    }

    public synchronized void produce(int newData) {
        while (!this.empty) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data = newData;
        this.empty = false;
        this.notify();
        //System.out.println("Produced:" + newData);
    }

    public synchronized int consume() {
        while (this.empty) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.empty = true;
        this.notify();
        if (isPrime(data))
            if (count < 10) {
                count++;
                System.out.println(count + " " + data);
            } else System.exit(0);
        return data;
    }
}

package com.nixalevel.exceptions;

import java.util.Random;

public class Retry {
    private int numberOfAllAttempts;
    private int counterOfAttempts;

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    private Block block;

    private boolean isDone;

    public int getCounterOfAttempts() {
        return counterOfAttempts;
    }

    public void setCounterOfAttempts(int counterOfAttempts) {
        this.counterOfAttempts = counterOfAttempts;
    }

    public Retry(Block block, int numberOfAllAttempts) {
        setBlock(block);
        setNumberOfAllAttempts(numberOfAllAttempts);
        setDone(false);
        setCounterOfAttempts(0);

    }

    public void executeBlockRunCode() {

    }

    public int getNumberOfAllAttempts() {
        return numberOfAllAttempts;
    }

    public void setNumberOfAllAttempts(int numberOfAllAttempts) {
        this.numberOfAllAttempts = numberOfAllAttempts;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public static void main(String[] args) {
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
        while (retry.getCounterOfAttempts() < retry.numberOfAllAttempts) {
            try {
                retry.getBlock().run();
                retry.setDone(true);
            } catch (Exception e) {
                retry.setCounterOfAttempts(retry.getCounterOfAttempts() + 1);
                if (retry.getCounterOfAttempts() < retry.getNumberOfAllAttempts())
                    continue;
                else {
                    System.out.println("All attempts was used!");
                    break;
                }
            } finally {
                if (retry.isDone()) {
                    retry.setCounterOfAttempts(retry.getCounterOfAttempts() + 1);
                    System.out.println("Run sucsessful! It was " + retry.getCounterOfAttempts() + " attempts");
                    break;
                }
            }
        }
    }
}

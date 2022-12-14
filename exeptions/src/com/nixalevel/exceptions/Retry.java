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
        isDone = false;
        setCounterOfAttempts(0);

    }

    public int getNumberOfAllAttempts() {
        return numberOfAllAttempts;
    }

    public void setNumberOfAllAttempts(int numberOfAllAttempts) {
        this.numberOfAllAttempts = numberOfAllAttempts;
    }

        public void runBlock() throws Exception {

        while (getCounterOfAttempts() < numberOfAllAttempts) {
            try {
                getBlock().run();
                isDone = true;
            } catch (Exception e) {
                Thread.sleep(100 * getCounterOfAttempts());
                setCounterOfAttempts(getCounterOfAttempts() + 1);
                if (getCounterOfAttempts() < getNumberOfAllAttempts())
                    continue;
                else {
                    System.out.println("All attempts was used!");
                    break;
                }
            } finally {
                if (isDone) {
                    setCounterOfAttempts(getCounterOfAttempts() + 1);
                    System.out.println("Run sucsessful! It was " + getCounterOfAttempts() + " attempts");
                    break;
                }
            }
        }
    }
}

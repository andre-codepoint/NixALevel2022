package com.NixALevel.Task02;

abstract class AbsSub implements BaseSub {
    private int temp;

    public AbsSub() {
        this(DEF_TEMP);
    }

    public AbsSub(int temp) {
        this.temp = temp;
    }

    @Override
    public State heatUp(int temp) {
        this.temp = this.temp + temp;
        State state;
        if (this.temp > toGas()) {
            state = State.GAS;
        } else if (this.temp > toLiquid()) {
            state = State.FLUID;
        } else {
            state = State.SOLID;
        }
        return state;
    }

    @Override
    public int getTemp() {
        return temp;
    }
    protected abstract int toGas();
    protected abstract int toLiquid();
}

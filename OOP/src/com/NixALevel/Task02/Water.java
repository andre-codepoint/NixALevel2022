package com.NixALevel.Task02;

public class Water extends AbsSub {
    private static final String NAME = "Water";
    private static final int GAS_TEMP = 100;
    private static final int LIQUID_TEMP = 0;

    @Override
    protected int toLiquid() {
        return LIQUID_TEMP;
    }

    @Override
    protected int toGas() {
        return GAS_TEMP;
    }

    @Override
    public String getName() {
        return NAME;
    }
}

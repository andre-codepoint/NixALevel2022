package com.NixALevel.Task02;

public class Iron extends AbsSub {
    private static final String NAME = "Iron";
    private static final int GAS_TEMP = 2862;
    private static final int LIQUID_TEMP = 1538;

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

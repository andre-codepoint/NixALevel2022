package com.NixALevel.Task02;

public class Oxygen extends AbsSub {
    private static final String NAME = "Oxygen";
    private static final int GAS_TEMP = -183;
    private static final int LIQUID_TEMP = -218;

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

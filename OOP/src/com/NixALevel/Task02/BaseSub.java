package com.NixALevel.Task02;

public interface BaseSub {
    int DEF_TEMP = 20;
    State heatUp(int temp);
    int getTemp();
    String getName();
}

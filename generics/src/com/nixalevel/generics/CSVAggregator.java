package com.nixalevel.generics;

import java.util.StringJoiner;

public class CSVAggregator<T> implements Aggregator<String, T> {

    @Override
    public String agregate(T[] items) {
        if (items == null) {
            return null;
        } else {
            if (items.length == 0) {
                return "";
            } else {
//                https://vertex-academy.com/tutorials/ru/java-8-stringjoiner/
                StringJoiner joiner = new StringJoiner(",");
                for (T n : items) {
                    if (n != null) {
                        joiner.add(n.toString());
                    }
                }
                return joiner.toString();
            }
        }
    }
}

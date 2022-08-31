package com.nixalevel.generics;

public class MaxAggregator<T extends Comparable<T>> implements Aggregator<T, T> {
    @Override
    public T agregate(T[] items) {
//          https://stackoverflow.com/questions/66084068/finding-max-of-array-using-generic-e-array
//          http://www.java2s.com/Tutorial/Java/0200__Generics/GenericmethodsMaxMin.htm
//          https://docs.oracle.com/javase/tutorial/java/generics/boundedTypeParams.html
        if ((items == null) & (items.length == 0)) {
            return null;
        } else {
            T comp = items[0];
            for (T e : items) {
                if (e != null) {
                    if (comp.compareTo(e) < 0) {
                        comp = e;
                    }
                }
            }
            return comp;
        }
    }
}

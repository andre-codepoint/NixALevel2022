package com.nixalevel.generics;

import java.util.*;

public class DistinctAggregator<T> implements Aggregator<Integer, T> {
    @Override
    public Integer agregate(T[] items) {
//  https://stackoverflow.com/questions/34478006/how-to-add-an-array-into-set-properly
        Set<T> set = new HashSet<>(Arrays.asList(items));
        return set.size();
    }
}

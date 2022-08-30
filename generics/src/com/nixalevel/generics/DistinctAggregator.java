package com.nixalevel.generics;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class DistinctAggregator<T> implements Aggregator<Integer,T>{
    @Override
    public Integer agregate(T[] items) {
        if (items==null) {
            return null;
        }
        else {
// https://www.javatpoint.com/find-unique-elements-in-array-java#:~:text=In%20Java%2C%20the%20simplest%20way,element%20from%20the%20hashmap%20keySet
// https://www.geeksforgeeks.org/count-distinct-elements-in-an-array/
// https://stackoverflow.com/questions/14656208/array-of-unique-elements
            return (new LinkedHashSet<T>(Arrays.asList(items))).toArray().length;
        }
    }
}

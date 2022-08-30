package com.nixalevel.generics;

public class AvgAggregator implements Aggregator<Double, Number> {
    @Override
    public Double agregate(Number[] items) {
//        https://stackoverflow.com/questions/62270648/calculate-average-method-for-a-generic-array
//        https://www.baeldung.com/java-array-sum-average
        if (items==null) {
            return null;
        }
        else {
            if (items.length==0) {
                return 0.0;
            }
            else {
                Double sum=0.0;
                int i=0;
                for (Number n:items) {
                    if (n!=null) {
                        sum = sum + n.doubleValue();
                        i++;
                    }
                }
                return sum/i;}
        }
    }
}

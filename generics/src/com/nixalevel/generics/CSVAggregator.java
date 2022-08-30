package com.nixalevel.generics;

public class CSVAggregator <T> implements Aggregator<String, T> {

    @Override
    public String agregate(T[] items) {
        if (items==null) {
            return null;
        }
        else {
            if (items.length==0) {
                return "";
            }
            else {
                String s= "";
                for (T n:items) {
                    if (n!=null) {
                        s = s + n.toString()+",";
                    }
                }
                return s.substring(0, s.length() - 1);
            }
        }
    }
}

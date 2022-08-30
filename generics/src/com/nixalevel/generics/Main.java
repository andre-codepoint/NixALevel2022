package com.nixalevel.generics;

public class Main {
    public static void main(String[] args) {
        System.out.println(new DistinctAggregator().agregate(new Object[]{1,1,1.0,null,1.0,null,"1","1",new Object(),new Object()}));
        System.out.println(new AvgAggregator().agregate(new Number[]{1,1,1.0,1.0,5,0,null}));
        System.out.println(new MaxAggregator().agregate(new Integer[]{1,1,null,1,null,1}));
        System.out.println(new CSVAggregator<>().agregate(new String[]{null,"nix","alevel",null," ","java",null}));
    }
}
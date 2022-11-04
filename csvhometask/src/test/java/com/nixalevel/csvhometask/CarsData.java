package com.nixalevel.csvhometask;

public class CarsData {

    @ActiveColumn("model")
    private String model;

    @ActiveColumn("fuel")
    private String fuel;

    @ActiveColumn("engine")
    private float engine;

    @ActiveColumn("maxspeed")
    private int maxspeed;

    @ActiveColumn("age")
    private int age;

    @ActiveColumn("price")
    private int price;


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Float getEngine() {
        return engine;
    }

    public void setEngine(Float engine) {
        this.engine = engine;
    }

    public int getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(int maxspeed) {
        this.maxspeed = maxspeed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

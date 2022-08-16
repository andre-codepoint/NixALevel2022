package com.NixALevel.Task01;

public class ContractStudent extends Student {
    private double valueOfEducation;

    public void setValueOfEducation(double valueOfEducation) {
        this.valueOfEducation = valueOfEducation;
    }

    public double getValueOfEducation() {
        return valueOfEducation;
    }

    @Override
    public void printValueOfEducation() {
        System.out.println(this.getName() + " "+ this.getValueOfEducation());
    }

    public ContractStudent(String name, int age, double valueOfEducation) {
        super(name, age);
        this.valueOfEducation = valueOfEducation;
    }
}

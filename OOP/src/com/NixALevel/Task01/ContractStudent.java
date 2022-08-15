package com.NixALevel.Task01;

public class ContractStudent extends Student {
    public double getValueOfEducation() {
        return valueOfEducation;
    }

    public void setValueOfEducation(double valueOfEducation) {
        this.valueOfEducation = valueOfEducation;
    }

    private double valueOfEducation;

    public ContractStudent(String name, int age, double valueOfEducation) {
        super(name, age);
        this.valueOfEducation = valueOfEducation;
    }
}

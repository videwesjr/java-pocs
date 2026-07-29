package com.structural.decorator;

public class SimpleCoffee implements Coffee {

    @Override
    public String getDescription() {
        return "Coffee";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}

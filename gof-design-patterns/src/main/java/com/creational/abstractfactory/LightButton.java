package com.creational.abstractfactory;

public class LightButton implements Button {

    @Override
    public void paint() {
        System.out.println("Rendering a light-themed button");
    }
}

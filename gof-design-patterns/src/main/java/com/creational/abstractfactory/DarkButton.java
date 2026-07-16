package com.creational.abstractfactory;

public class DarkButton implements Button {

    @Override
    public void paint() {
        System.out.println("Rendering a dark-themed button");
    }
}

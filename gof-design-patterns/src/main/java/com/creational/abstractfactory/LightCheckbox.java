package com.creational.abstractfactory;

public class LightCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("Rendering a light-themed checkbox");
    }
}

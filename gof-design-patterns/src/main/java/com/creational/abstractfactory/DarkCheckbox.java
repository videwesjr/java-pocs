package com.creational.abstractfactory;

public class DarkCheckbox implements Checkbox {

    @Override
    public void paint() {
        System.out.println("Rendering a dark-themed checkbox");
    }
}

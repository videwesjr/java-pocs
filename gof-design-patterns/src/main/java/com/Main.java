package com;

import com.creational.abstractfactory.AbstractFactoryDemo;
import com.creational.factorymethod.FactoryMethodDemo;
import com.creational.singleton.SingletonDemo;

public class Main {
    public static void main(String[] args) {
        SingletonDemo.run();
        System.out.println();
        FactoryMethodDemo.run();
        System.out.println();
        AbstractFactoryDemo.run();
    }
}

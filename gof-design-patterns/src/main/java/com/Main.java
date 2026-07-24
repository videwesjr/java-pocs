package com;

import com.creational.abstractfactory.AbstractFactoryDemo;
import com.creational.builder.BuilderDemo;
import com.creational.factorymethod.FactoryMethodDemo;
import com.creational.prototype.PrototypeDemo;
import com.creational.singleton.SingletonDemo;
import com.structural.adapter.AdapterDemo;
import com.structural.bridge.BridgeDemo;
import com.structural.composite.CompositeDemo;

public class Main {
    public static void main(String[] args) {
        SingletonDemo.run();
        System.out.println();
        FactoryMethodDemo.run();
        System.out.println();
        AbstractFactoryDemo.run();
        System.out.println();
        BuilderDemo.run();
        System.out.println();
        PrototypeDemo.run();
        System.out.println();
        AdapterDemo.run();
        System.out.println();
        BridgeDemo.run();
        System.out.println();
        CompositeDemo.run();
    }
}

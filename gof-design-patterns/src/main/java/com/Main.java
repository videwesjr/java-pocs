package com;

import com.creational.abstractfactory.AbstractFactoryDemo;
import com.creational.builder.BuilderDemo;
import com.creational.factorymethod.FactoryMethodDemo;
import com.creational.prototype.PrototypeDemo;
import com.creational.singleton.SingletonDemo;
import com.structural.adapter.AdapterDemo;
import com.structural.bridge.BridgeDemo;
import com.structural.composite.CompositeDemo;
import com.structural.decorator.DecoratorDemo;
import com.structural.facade.FacadeDemo;
import com.structural.flyweight.FlyweightDemo;

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
        System.out.println();
        DecoratorDemo.run();
        System.out.println();
        FacadeDemo.run();
        System.out.println();
        FlyweightDemo.run();
    }
}

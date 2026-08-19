package com;

import com.behavioral.chainofresponsibility.ChainOfResponsibilityDemo;
import com.behavioral.command.CommandDemo;
import com.behavioral.interpreter.InterpreterDemo;
import com.behavioral.iterator.IteratorDemo;
import com.behavioral.mediator.MediatorDemo;
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
import com.structural.proxy.ProxyDemo;

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
        System.out.println();
        ProxyDemo.run();
        System.out.println();
        ChainOfResponsibilityDemo.run();
        System.out.println();
        CommandDemo.run();
        System.out.println();
        InterpreterDemo.run();
        System.out.println();
        IteratorDemo.run();
        System.out.println();
        MediatorDemo.run();
    }
}

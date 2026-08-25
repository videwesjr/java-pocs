package com.behavioral.observer;

public final class PhoneDisplay implements Observer {

    @Override
    public void update(int temperature, int humidity) {
        System.out.println("Phone display: " + temperature + "°C, " + humidity + "% humidity");
    }
}

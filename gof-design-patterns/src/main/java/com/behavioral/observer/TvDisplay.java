package com.behavioral.observer;

public final class TvDisplay implements Observer {

    @Override
    public void update(int temperature, int humidity) {
        System.out.println("TV display: " + temperature + "°C, " + humidity + "% humidity");
    }
}

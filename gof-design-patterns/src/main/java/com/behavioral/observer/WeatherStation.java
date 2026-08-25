package com.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public final class WeatherStation implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private int temperature;
    private int humidity;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity);
        }
    }

    public void setMeasurements(int temperature, int humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers();
    }
}

package com.behavioral.observer;

public final class ObserverDemo {

    private ObserverDemo() {
    }

    public static void run() {
        System.out.println("=== Observer ===");

        WeatherStation station = new WeatherStation();

        Observer phone = new PhoneDisplay();
        Observer tv = new TvDisplay();

        station.registerObserver(phone);
        station.registerObserver(tv);

        station.setMeasurements(24, 65);

        station.removeObserver(tv);
        station.setMeasurements(27, 70);
    }
}

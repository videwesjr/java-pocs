package com.creational.abstractfactory;

public final class AbstractFactoryDemo {

    private AbstractFactoryDemo() {
    }

    public static void run() {
        System.out.println("=== Abstract Factory ===");

        renderUi(new LightThemeFactory());
        renderUi(new DarkThemeFactory());
    }

    private static void renderUi(GUIFactory factory) {
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.paint();
        checkbox.paint();
    }
}

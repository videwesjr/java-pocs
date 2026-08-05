package com.structural.proxy;

public final class ProxyDemo {

    private ProxyDemo() {
    }

    public static void run() {
        System.out.println("=== Proxy ===");

        Image photo1 = new ProxyImage("photo1.jpg");
        Image photo2 = new ProxyImage("photo2.jpg");

        System.out.println("Images created, nothing loaded yet.");

        System.out.println("First call to display photo1:");
        photo1.display();

        System.out.println("Second call to display photo1 (already loaded, no disk hit):");
        photo1.display();

        System.out.println("First call to display photo2:");
        photo2.display();
    }
}

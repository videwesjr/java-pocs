package com.extra.nullobject;

import java.util.Map;

public final class CustomerRegistry {

    private final Map<String, Customer> customers;

    public CustomerRegistry(Map<String, Customer> customers) {
        this.customers = customers;
    }

    public Customer find(String name) {
        return customers.getOrDefault(name, new NullCustomer());
    }
}

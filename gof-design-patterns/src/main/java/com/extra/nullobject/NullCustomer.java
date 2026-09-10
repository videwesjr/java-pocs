package com.extra.nullobject;

public final class NullCustomer implements Customer {

    @Override
    public String getName() {
        return "Not Available";
    }

    @Override
    public boolean isNull() {
        return true;
    }
}

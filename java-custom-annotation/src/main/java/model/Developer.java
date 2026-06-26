package model;

import annotation.Role;

@Role("DEV")
public class Developer {

    private final String name;

    public Developer(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return "Developer{name='" + name + "'}";
    }
}

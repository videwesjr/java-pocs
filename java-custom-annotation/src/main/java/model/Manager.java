package model;

import annotation.Role;

@Role("ADMIN")
@Role("MANAGER")
public class Manager {

    private final String name;

    public Manager(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return "Manager{name='" + name + "'}";
    }
}

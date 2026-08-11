package com.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;

public class Context {

    private final Map<String, Integer> variables = new HashMap<>();

    public void assign(String variable, int value) {
        variables.put(variable, value);
    }

    public int lookup(String variable) {
        Integer value = variables.get(variable);
        if (value == null) {
            throw new IllegalArgumentException("Unknown variable: " + variable);
        }
        return value;
    }
}

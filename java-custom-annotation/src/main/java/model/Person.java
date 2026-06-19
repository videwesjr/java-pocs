package model;

import annotation.CPF;

public class Person {

    private String name;

    @CPF
    private String cpf;

    public Person(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public String getName() { return name; }
    public String getCpf() { return cpf; }

    @Override
    public String toString() {
        return "Person{name='" + name + "', cpf='" + cpf + "'}";
    }
}

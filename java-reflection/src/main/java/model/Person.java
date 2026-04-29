package model;

public class Person {

    public String name;
    private int age;
    private String email;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    private String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    private void birthday() {
        this.age++;
    }

    @Override
    public String toString() {
        return "Person{name=" + name + ", age=" + age + ", email=" + email + "}";
    }
}
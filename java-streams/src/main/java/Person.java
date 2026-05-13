public class Person {

    public String name;
    public int age;
    public String email;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void birthday() {
        this.age++;
    }

    @Override
    public String toString() {
        return "Person{name=" + name + ", age=" + age + ", email=" + email + "}";
    }
}
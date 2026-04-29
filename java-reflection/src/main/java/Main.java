import model.Person;

public class Main {

    public static void main(String[] args) throws Exception {

        Person person = new Person("Alice", 30, "alice@email.com");
        Reflector personReflector = new Reflector(Person.class);

        System.out.println("Inspect Person");
        personReflector.inspect();

        System.out.println("\nRead private field: email");
        System.out.println(personReflector.getField(person, "email"));

        System.out.println("\nInvoke private method: getEmail");
        System.out.println(personReflector.invokeMethod(person, "getEmail"));

        System.out.println("\nInvoke private method: birthday");
        System.out.println("Before: " + person.getAge());
        personReflector.invokeMethod(person, "birthday");
        System.out.println("After : " + person.getAge());

        System.out.println("\nModify private field: email");
        personReflector.setField(person, "email", "alice.new@email.com");
        System.out.println(personReflector.getField(person, "email"));

        System.out.println("\nInstantiate Person via Reflection");
        Object newPerson = personReflector.newInstance(
                new Class<?>[]{String.class, int.class, String.class},
                "Joao", 25, "joao@email.com"
        );
        System.out.println(newPerson);
    }
}
import java.util.function.*;

public class FunctionalInterfaces {

    // Custom functional interface - use when the method name improves readability over test(), apply(), etc.
    @FunctionalInterface
    public interface PersonValidator {
        boolean validate(Person person);
    }

    // Predicate - evaluates a condition and returns true or false
    public Predicate<Person> isAdult() {
        return person -> person.getAge() >= 18;
    }

    // Function - transforms an input into a different output type
    // method reference - instance method of arbitrary type
    public Function<Person, String> toName() {
        return Person::getName;
    }

    // BiFunction - same as Function but accepts two inputs
    public BiFunction<Person, String, String> greetWith() {
        return (person, greeting) -> greeting + ", " + person.getName() + "!";
    }

    // Consumer - receives an input and performs a side effect, returns nothing
    // method reference - instance method of specific object
    public Consumer<Person> printPerson() {
        return System.out::println;
    }

    // BiConsumer - same as Consumer but accepts two inputs
    public BiConsumer<Person, String> printWithLabel() {
        return (person, label) -> System.out.println("[" + label + "] " + person.getName());
    }

    // Supplier - produces a value without receiving any input
    // method reference - constructor
    public Supplier<Person> defaultPerson() {
        return () -> new Person("Unknown", 0, "unknown@email.com");
    }

    // UnaryOperator - transforms an input into the same type
    public UnaryOperator<Person> anonymize() {
        return person -> new Person("***", person.getAge(), "***@***.com");
    }

    // BinaryOperator - takes two inputs of the same type and returns one of the same type
    public BinaryOperator<Person> oldest() {
        return (a, b) -> a.getAge() >= b.getAge() ? a : b;
    }
}
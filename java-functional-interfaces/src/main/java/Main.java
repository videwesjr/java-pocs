import java.util.List;

public class Main {

    public static void main(String[] args) {

        FunctionalInterfaces fi = new FunctionalInterfaces();

        List<Person> people = List.of(
                new Person("Carlos", 30, "carlos@email.com"),
                new Person("Joao", 17, "joao@email.com"),
                new Person("Fernanda", 25, "fernanda@email.com"),
                new Person("Lucas", 15, "lucas@email.com")
        );

        System.out.println("@FunctionalInterface ");
        FunctionalInterfaces.PersonValidator isAdult = person -> person.getAge() >= 18;
        people.forEach(p -> System.out.println(p.getName() + ": " + isAdult.validate(p)));

        System.out.println("\nPredicate ");
        people.stream()
                .filter(fi.isAdult())
                .forEach(p -> System.out.println(p.getName() + " - age: " + p.getAge()));

        System.out.println("\nFunction ");
        people.stream()
                .map(fi.toName())
                .forEach(System.out::println);

        System.out.println("\nBiFunction ");
        System.out.println(fi.greetWith().apply(people.getFirst(), "Hello"));

        System.out.println("\nConsumer ");
        people.forEach(fi.printPerson());

        System.out.println("\nBiConsumer ");
        fi.printWithLabel().accept(people.getFirst(), "ADMIN");

        System.out.println("\nSupplier ");
        System.out.println(fi.defaultPerson().get());

        System.out.println("\nUnaryOperator ");
        people.stream()
                .map(fi.anonymize())
                .forEach(p -> System.out.println(p.getName() + " | " + p.getEmail()));

        System.out.println("\nBinaryOperator ");
        Person oldest = people.stream().reduce(fi.oldest()).orElseThrow();
        System.out.println("Oldest: " + oldest.getName() + " - age: " + oldest.getAge());
    }
}
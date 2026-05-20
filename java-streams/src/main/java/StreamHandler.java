import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamHandler {

    public List<String> toNameList(List<Person> people) {
        return people.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
    }

    public List<Person> sortByAge(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .collect(Collectors.toList());
    }

    public Optional<Person> findFirst(List<Person> people, Predicate<Person> predicate) {
        return people.stream()
                .filter(predicate)
                .findFirst();
    }

    public long count(List<Person> people, Predicate<Person> predicate) {
        return people.stream()
                .filter(predicate)
                .count();
    }

    public boolean anyMatch(List<Person> people, Predicate<Person> predicate) {
        return people.stream().anyMatch(predicate);
    }

    public boolean allMatch(List<Person> people, Predicate<Person> predicate) {
        return people.stream().allMatch(predicate);
    }

    public boolean noneMatch(List<Person> people, Predicate<Person> predicate) {
        return people.stream().noneMatch(predicate);
    }

    public List<Person> fromArray(Person[] people) {
        return Arrays.stream(people)
                .collect(Collectors.toList());
    }

    public Person[] toArray(List<Person> people) {
        return people.toArray(Person[]::new);
    }

    public List<String> mapToUpperCaseNames(List<Person> people) {
        return people.stream()
                .map(Person::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public Map<String, Integer> mapToNameAge(List<Person> people) {
        return people.stream()
                .collect(Collectors.toMap(Person::getName, Person::getAge));
    }

    public Map<Boolean, List<Person>> partitionByAdult(List<Person> people) {
        return people.stream()
                .collect(Collectors.partitioningBy(person -> person.getAge() >= 18));
    }

    public Map<String, List<Person>> groupByEmailDomain(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(person -> person.getEmail().split("@")[1]));
    }

    public List<Person> filter(List<Person> people, Predicate<Person> predicate) {
        return people.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<Person> filterAndSort(List<Person> people, Predicate<Person> predicate) {
        return people.stream()
                .filter(predicate)
                .sorted(Comparator.comparingInt(Person::getAge))
                .collect(Collectors.toList());
    }

    public Predicate<Person> isAdult() {
        return person -> person.getAge() >= 18;
    }

    public Predicate<Person> hasEmailDomain(String domain) {
        return person -> person.getEmail().endsWith(domain);
    }

    public Predicate<Person> nameLongerThan(int length) {
        return person -> person.getName().length() > length;
    }

    public List<Person> supplierGenerate(Supplier<Person> supplier, int limit) {
        return Stream.generate(supplier)
                .limit(limit)
                .collect(Collectors.toList());
    }

    // Consumer - receives an input and performs a side effect, returns nothing
    public void printPersonNameAndAge(List<Person> people) {
        people.forEach(p -> System.out.println(p.getName() + " | " + p.getAge()));
    }

    // Consumer - peek inspects each element mid-pipeline without changing it, useful for debugging
    public void peekPersonsWithFilter(List<Person> people) {
        people.stream()
                .peek(p -> System.out.println("before filter: " + p.getName() + " | " + p.getAge()))
                .filter(p -> p.getAge() >= 18)
                .peek(p -> System.out.println("after filter : " + p.getName() + " | " + p.getAge()));
    }

    // BinaryOperator - takes two inputs of the same type and returns one of the same type
    public Person oldest(List<Person> people) {
        return people.stream()
                .reduce((a, b) -> a.getAge() >= b.getAge() ? a : b)
                .orElseThrow();
    }

    // UnaryOperator - transform each element into the same type
    public List<Person> anonymize(List<Person> people) {
        UnaryOperator<Person> anonymize = p -> new Person(p.getName(), p.getAge(), "***@***.com");
        return people.stream()
                .map(anonymize)
                .toList();
    }
}
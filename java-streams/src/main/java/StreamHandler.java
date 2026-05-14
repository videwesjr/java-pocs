import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
}
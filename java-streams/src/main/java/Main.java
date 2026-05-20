import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        StreamHandler sh = new StreamHandler();

        List<Person> people = List.of(
                new Person("Carlos", 30, "carlos@gmail.com"),
                new Person("Joao", 17, "joao@gmail.com"),
                new Person("Fernanda", 25, "fernanda@company.com"),
                new Person("Lucas", 15, "lucas@company.com"),
                new Person("Ana", 22, "ana@gmail.com")
        );

        System.out.println("=== Lists ===");
        System.out.println("toNameList: " + sh.toNameList(people));

        System.out.println("\nsortByAge:");
        sh.sortByAge(people).forEach(p -> System.out.println(p.getName() + " - " + p.getAge()));

        System.out.println("\nfindFirst (age > 20):");
        Optional<Person> first = sh.findFirst(people, p -> p.getAge() > 20);
        first.ifPresent(p -> System.out.println(p.getName()));

        System.out.println("\ncount (adults): " + sh.count(people, sh.isAdult()));
        System.out.println("anyMatch (age > 28): " + sh.anyMatch(people, p -> p.getAge() > 28));
        System.out.println("allMatch (age > 10): " + sh.allMatch(people, p -> p.getAge() > 10));
        System.out.println("noneMatch (age > 50): " + sh.noneMatch(people, p -> p.getAge() > 50));

        System.out.println("\n=== Arrays ===");
        Person[] array = sh.toArray(people);
        System.out.println("toArray length: " + array.length);

        List<Person> fromArray = sh.fromArray(array);
        System.out.println("fromArray size: " + fromArray.size());

        System.out.println("\n=== Map ===");
        System.out.println("mapToUpperCaseNames: " + sh.mapToUpperCaseNames(people));

        System.out.println("\nmapToNameAge:");
        sh.mapToNameAge(people).forEach((name, age) -> System.out.println(name + " -> " + age));

        System.out.println("\npartitionByAdult:");
        Map<Boolean, List<Person>> partitioned = sh.partitionByAdult(people);
        System.out.println("Adults   : " + partitioned.get(true).stream().map(Person::getName).toList());
        System.out.println("Minors   : " + partitioned.get(false).stream().map(Person::getName).toList());

        System.out.println("\ngroupByEmailDomain:");
        sh.groupByEmailDomain(people).forEach((domain, persons) ->
                System.out.println(domain + " -> " + persons.stream().map(Person::getName).toList()));

        System.out.println("\n=== Filter ===");
        System.out.println("filter (adults):");
        sh.filter(people, sh.isAdult()).forEach(p -> System.out.println(p.getName()));

        System.out.println("\nfilterAndSort (adults by age):");
        sh.filterAndSort(people, sh.isAdult()).forEach(p -> System.out.println(p.getName() + " - " + p.getAge()));

        System.out.println("\n=== Predicates ===");
        System.out.println("isAdult AND @gmail.com:");
        sh.filter(people, sh.isAdult().and(sh.hasEmailDomain("@gmail.com")))
                .forEach(p -> System.out.println(p.getName()));

        System.out.println("\nisAdult OR nameLongerThan(5):");
        sh.filter(people, sh.isAdult().or(sh.nameLongerThan(5)))
                .forEach(p -> System.out.println(p.getName()));

        System.out.println("\nnegate (NOT adult):");
        sh.filter(people, sh.isAdult().negate())
                .forEach(p -> System.out.println(p.getName()));

        System.out.println("\n=== Supplier ===");
        System.out.println("\ngenerate:");
        Supplier<Person> personSupplier = () -> new Person("Carlos", 30, "carlos@gmail.com");
        sh.supplierGenerate(personSupplier, 3)
                .forEach(System.out::println);

        System.out.println("\n=== Consumer - forEach ===");
        sh.printPersonNameAndAge(people);

        System.out.println("\n=== Consumer - peek ===");
        sh.peekPersonsWithFilter(people);

        System.out.println("\n=== BinaryOperator ===");
        Person oldest = sh.oldest(people);
        System.out.println("Oldest  : " + oldest.getName() + " - age: " + oldest.getAge());

    }
}
import annotation.Role;
import model.Developer;
import model.Guest;
import model.Manager;
import model.Person;
import validator.RoleChecker;
import validator.Validator;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        runCpfDemo();
        System.out.println();
        runRoleDemo();
    }

    private static void runCpfDemo() throws IllegalAccessException {
        System.out.println("=== @CPF Validation ===");
        Validator validator = new Validator();

        run(validator, new Person("Carlos Silva", "529.982.247-25"));
        run(validator, new Person("Ana Souza",    "52998224725"));
        run(validator, new Person("Bob",          "111.111.111-11"));
        run(validator, new Person("Diana",        "123.456.789-00"));
        run(validator, new Person("Eve",          null));
        run(validator, new Person("Frank",        "529.982.247-2"));
    }

    private static void run(Validator validator, Person person) throws IllegalAccessException {
        var violations = validator.validate(person);
        if (violations.isEmpty()) {
            System.out.println("[VALID]   " + person);
        } else {
            System.out.println("[INVALID] " + person);
            violations.forEach(v -> System.out.println("          -> " + v));
        }
    }

    private static void runRoleDemo() {
        System.out.println("=== @Role (Repeatable) ===");
        RoleChecker checker = new RoleChecker();

        printRoles(checker, Manager.class,   new Manager("Alice"));
        printRoles(checker, Developer.class, new Developer("Bob"));
        printRoles(checker, Guest.class,     new Guest("Eve"));

        System.out.println();
        System.out.println("--- Access control ---");
        checkAccess(checker, Manager.class,   "ADMIN");
        checkAccess(checker, Manager.class,   "DEV");
        checkAccess(checker, Developer.class, "DEV");
        checkAccess(checker, Developer.class, "ADMIN");
        checkAccess(checker, Guest.class,     "DEV");

        System.out.println();
        System.out.println("--- Non-obvious: getAnnotation vs getAnnotationsByType ---");

        Role   direct = Manager.class.getAnnotation(Role.class);
        Role[] byType = Manager.class.getAnnotationsByType(Role.class);
        System.out.println("Manager.getAnnotation(Role.class)        = " + direct);
        System.out.println("Manager.getAnnotationsByType(Role.class) = " + Arrays.toString(byType));
    }

    private static void printRoles(RoleChecker checker, Class<?> clazz, Object instance) {
        var roles = checker.getRoles(clazz);
        System.out.println(instance + " -> roles: " + (roles.isEmpty() ? "[none]" : roles));
    }

    private static void checkAccess(RoleChecker checker, Class<?> clazz, String role) {
        boolean allowed = checker.hasRole(clazz, role);
        System.out.printf("%-12s requires %-10s -> %s%n",
                clazz.getSimpleName(), role, allowed ? "GRANTED" : "DENIED");
    }
}

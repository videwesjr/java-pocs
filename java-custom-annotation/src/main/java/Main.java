import model.Person;
import validator.Validator;

public class Main {

    public static void main(String[] args) throws IllegalAccessException {
        Validator validator = new Validator();

        run(validator, new Person("Carlos Silva",  "529.982.247-25"));
        run(validator, new Person("Ana Souza",     "52998224725"));
        run(validator, new Person("Bob",           "111.111.111-11"));
        run(validator, new Person("Diana",         "123.456.789-00"));
        run(validator, new Person("Eve",           null));
        run(validator, new Person("Frank",         "529.982.247-2"));
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
}

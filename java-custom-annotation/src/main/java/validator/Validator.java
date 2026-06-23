package validator;

import annotation.CPF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {

    private final CPFValidator cpfValidator = new CPFValidator();

    public List<ValidationResult> validate(Object obj) {
        var violations = new ArrayList<ValidationResult>();

        Arrays.stream(obj.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(CPF.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        String cpf = field.get(obj) instanceof String s ? s : null;
                        if (!cpfValidator.isValid(cpf)) {
                            String message = field.getAnnotation(CPF.class).message();
                            violations.add(new ValidationResult(field.getName(), "%s (value: '%s')".formatted(message, cpf)));
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });

        return violations;
    }
}

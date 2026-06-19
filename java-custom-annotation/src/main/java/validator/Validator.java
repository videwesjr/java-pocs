package validator;

import annotation.CPF;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {

    private final CPFValidator cpfValidator = new CPFValidator();

    public List<ValidationResult> validate(Object obj) throws IllegalAccessException {
        var violations = new ArrayList<ValidationResult>();

        for (Field field : Arrays.stream(obj.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(CPF.class))
                .toList()) {
            field.setAccessible(true);
            String cpf = field.get(obj) instanceof String s ? s : null;
            if (!cpfValidator.isValid(cpf)) {
                String message = field.getAnnotation(CPF.class).message();
                violations.add(new ValidationResult(field.getName(), message + " (value: '" + cpf + "')"));
            }
        }

        return violations;
    }
}

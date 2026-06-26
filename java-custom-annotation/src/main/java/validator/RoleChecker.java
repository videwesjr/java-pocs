package validator;

import annotation.Role;

import java.util.Arrays;
import java.util.List;

public class RoleChecker {

    public List<String> getRoles(Class<?> clazz) {
        return Arrays.stream(clazz.getAnnotationsByType(Role.class))
                .map(Role::value)
                .toList();
    }

    public boolean hasRole(Class<?> clazz, String role) {
        return Arrays.stream(clazz.getAnnotationsByType(Role.class))
                .anyMatch(r -> r.value().equals(role));
    }
}

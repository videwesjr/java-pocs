package validator;

public class ValidationResult {
    private final String field;
    private final String message;

    public ValidationResult(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() { return field; }
    public String getMessage() { return message; }

    @Override
    public String toString() {
        return "Field '" + field + "': " + message;
    }
}

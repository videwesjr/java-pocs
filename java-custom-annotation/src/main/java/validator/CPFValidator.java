package validator;

public class CPFValidator {

    public boolean isValid(String cpf) {
        if (cpf == null) return false;

        String digits = cpf.replaceAll("[.\\-]", "");

        if (digits.length() != 11) return false;
        if (!digits.matches("\\d{11}")) return false;
        if (allSameDigits(digits)) return false;

        return checkDigit(digits, 9) && checkDigit(digits, 10);
    }

    // Verifies the check digit at the given position (9 = first, 10 = second)
    private boolean checkDigit(String digits, int position) {
        int sum = 0;
        for (int i = 0; i < position; i++) {
            sum += Character.getNumericValue(digits.charAt(i)) * (position + 1 - i);
        }
        int remainder = sum % 11;
        int expected = remainder < 2 ? 0 : 11 - remainder;
        return Character.getNumericValue(digits.charAt(position)) == expected;
    }

    private boolean allSameDigits(String digits) {
        return digits.chars().distinct().count() == 1;
    }
}

## java-custom-annotation

Java POC showing how to create and use **custom annotations** with runtime retention and reflection-based validation — demonstrated through a `@CPF` field validator.

### Annotation

- `@CPF` — marks a `String` field as a CPF (Brazilian tax ID). Retained at runtime (`RetentionPolicy.RUNTIME`) and targets fields (`ElementType.FIELD`). Accepts an optional `message` attribute that defaults to `"Invalid CPF"`.

### Validation

- `CPFValidator` — validates a CPF string: strips formatting (`.-`), checks length (11 digits), rejects all-same-digit sequences, and verifies both check digits using the standard modulo-11 algorithm.
- `Validator` — uses reflection to scan any object's declared fields for `@CPF`, invokes `CPFValidator`, and collects `ValidationResult` violations.
- `ValidationResult` — holds the field name and error message for each violation.

### How it works

1. Annotate any `String` field with `@CPF`.
2. Pass an instance of that class to `Validator#validate(Object)`.
3. Reflection finds all `@CPF`-annotated fields, reads their values, and runs the CPF check.
4. Returns a list of violations (empty list means all fields are valid).

```java
public class Person {
    @CPF
    private String cpf;
}

var violations = new Validator().validate(new Person("Carlos", "529.982.247-25"));
// violations is empty → valid
```

---

### Build
```bash
./gradlew build
```

### Result

```
[VALID]   Person{name='Carlos Silva', cpf='529.982.247-25'}
[VALID]   Person{name='Ana Souza', cpf='52998224725'}
[INVALID] Person{name='Bob', cpf='111.111.111-11'}
          -> Field 'cpf': Invalid CPF (value: '111.111.111-11')
[INVALID] Person{name='Diana', cpf='123.456.789-00'}
          -> Field 'cpf': Invalid CPF (value: '123.456.789-00')
[INVALID] Person{name='Eve', cpf='null'}
          -> Field 'cpf': Invalid CPF (value: 'null')
[INVALID] Person{name='Frank', cpf='529.982.247-2'}
          -> Field 'cpf': Invalid CPF (value: '529.982.247-2')
```

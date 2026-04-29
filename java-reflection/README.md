## java-reflection

Java 26 POC showing how to use the Reflection API to read, invoke, and modify classes at runtime via a custom `Reflector` class.

- Inspects fields, methods, and constructors of a class
- Reads private fields without direct access
- Invokes private methods at runtime
- Modifies private fields at runtime
- Instantiates classes dynamically via reflection

---

### Build
```bash
./gradlew build
```

### Run
```bash
./gradlew run
```

### Result

```
> Task :run
Inspect Person
Class      : model.Person
Superclass : Object
Fields     :
  [public] String name
  [private] int age
  [private] String email
Methods    :
  [public] String getName()
  [public] String toString()
  [private] void birthday()
  [public] int getAge()
  [private] String getEmail()
Constructors:
  model.Person([class java.lang.String, int, class java.lang.String])

Read private field: email
alice@email.com

Invoke private method: getEmail
alice@email.com

Invoke private method: birthday
Before: 30
After : 31

Modify private field: email
alice.new@email.com

Instantiate Person via Reflection
Person{name=Joao, age=25, email=joao@email.com}

BUILD SUCCESSFUL in 1s
```
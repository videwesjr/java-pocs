## java-functional-interfaces

Java 26 POC showing how to use the most common Functional Interfaces from `java.util.function` applied to a `Person` model.

- `@FunctionalInterface` — custom interface with a single abstract method
- `Predicate<T>` — evaluates a condition and returns true or false
- `Function<T,R>` — transforms an input into a different output type
- `BiFunction<T,U,R>` — same as Function but accepts two inputs
- `Consumer<T>` — receives an input and performs a side effect, returns nothing
- `BiConsumer<T,U>` — same as Consumer but accepts two inputs
- `Supplier<T>` — produces a value without receiving any input
- `UnaryOperator<T>` — transforms an input into the same type
- `BinaryOperator<T>` — takes two inputs of the same type and returns one of the same type

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
=== @FunctionalInterface ===
Carlos: true
Joao: false
Fernanda: true
Lucas: false

=== Predicate ===
Carlos - age: 30
Fernanda - age: 25

=== Function ===
Carlos
Joao
Fernanda
Lucas

=== BiFunction ===
Hello, Carlos!

=== Consumer ===
Person{name=Carlos, age=30, email=carlos@email.com}
Person{name=Joao, age=17, email=joao@email.com}
Person{name=Fernanda, age=25, email=fernanda@email.com}
Person{name=Lucas, age=15, email=lucas@email.com}

=== BiConsumer ===
[ADMIN] Carlos

=== Supplier ===
Person{name=Unknown, age=0, email=unknown@email.com}

=== UnaryOperator ===
*** | ***@***.com
*** | ***@***.com
*** | ***@***.com
*** | ***@***.com

=== BinaryOperator ===
Oldest: Carlos - age: 30

BUILD SUCCESSFUL in 1s
```
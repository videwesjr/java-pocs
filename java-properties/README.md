## java-properties

Java POC showing how to read and write `.properties` files using `java.util.Properties` via a custom `PropertiesHandler` class.

- Reads project metadata from `application.properties`
- Creates a new `app.properties` file at runtime
- Reads back the created file from disk

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

`build/resources/main/application.properties`
```properties
app.name=java-properties-poc
app.version=1.0.0
java.version=26
```

```
> Task :run
Reading from application.properties
app.name: java-properties-poc
app.version: 1.0.0
java.version: 26
------------------------------------
app.properties file created.
Reading from app.properties
env: dev
test-property: test


BUILD SUCCESSFUL in 1s
```
## java-streams

Java 26 POC showing how to use the Streams API with Lists, Arrays, Map, Filter, and Predicates applied to a `Person` model.

- `Lists` — toNameList, sortByAge, findFirst, count, anyMatch, allMatch, noneMatch
- `Arrays` — toArray, fromArray
- `Map` — mapToUpperCaseNames, mapToNameAge, partitionByAdult, groupByEmailDomain
- `Filter` — filter, filterAndSort
- `Predicates` — isAdult, hasEmailDomain, nameLongerThan, composed with and/or/negate

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
=== Lists ===
toNameList: [Carlos, Joao, Fernanda, Lucas, Ana]

sortByAge:
Lucas - 15
Joao - 17
Ana - 22
Fernanda - 25
Carlos - 30

findFirst (age > 20):
Carlos

count (adults): 3
anyMatch (age > 28): true
allMatch (age > 10): true
noneMatch (age > 50): true

=== Arrays ===
toArray length: 5
fromArray size: 5

=== Map ===
mapToUpperCaseNames: [CARLOS, JOAO, FERNANDA, LUCAS, ANA]

mapToNameAge:
Carlos -> 30
Joao -> 17
Fernanda -> 25
Lucas -> 15
Ana -> 22

partitionByAdult:
Adults   : [Carlos, Fernanda, Ana]
Minors   : [Joao, Lucas]

groupByEmailDomain:
gmail.com -> [Carlos, Joao, Ana]
company.com -> [Fernanda, Lucas]

=== Filter ===
filter (adults):
Carlos
Fernanda
Ana

filterAndSort (adults by age):
Ana - 22
Fernanda - 25
Carlos - 30

=== Predicates ===
isAdult AND @gmail.com:
Carlos
Ana

isAdult OR nameLongerThan(5):
Carlos
Fernanda
Ana

negate (NOT adult):
Joao
Lucas

BUILD SUCCESSFUL in 1s
```
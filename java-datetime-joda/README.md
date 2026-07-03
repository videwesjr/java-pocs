## java-datetime-joda

Java POC comparing the modern `java.time` (JSR-310) API with **Joda-Time**, side by side, across creation/parsing, arithmetic/comparison, and conversions between the two.

- `JavaTimeHandler` — creation/parsing with `java.time`: `LocalDate`/`LocalTime`/`LocalDateTime` (`now()`/`of(...)`), `ZonedDateTime` + `ZoneId`, `Instant`, parsing/formatting with `DateTimeFormatter`
- `JodaTimeHandler` — the same operations with Joda-Time: `LocalDate`/`LocalTime`/`DateTime` (`now()`/constructors), `DateTime` + `DateTimeZone`, `Instant`, parsing/formatting with `DateTimeFormat`

#### Core creation & parsing
- Create dates/times: `LocalDate`, `LocalTime`, `LocalDateTime` (`java.time`) vs `DateTime`, `LocalDate` (Joda) — `now()` and `of(...)` factories
- Time zones: `ZonedDateTime` + `ZoneId` (`java.time`) vs `DateTime` + `DateTimeZone` (Joda)
- Instants/epoch: `Instant` (`java.time`) vs `Instant` (Joda)
- Parsing and formatting with `DateTimeFormatter` (`java.time`) vs `DateTimeFormat` (Joda)

#### Arithmetic & comparison
- Adding/subtracting time: `Period` and `Duration` (`java.time`) vs `Period` and `Duration` (Joda)
- Comparing dates/times: `isBefore` / `isAfter` / `compareTo` in both APIs
- Date ranges: `Interval` (Joda) vs manual range checks with `java.time`

#### Conversions
- Legacy `Date` / `Calendar` → `java.time` → Joda, and back
- Joda `DateTime` ↔ `java.time.ZonedDateTime`
- Epoch millis / timestamps across `Date`, `java.time`, and Joda

---

### Build
```bash
./gradlew build
```

### Run
```bash
./gradlew run
```

### Result Core creation & parsing

```
=== Creation: LocalDate / LocalTime / LocalDateTime ===
java.time now()  : 2026-07-01 | 21:54:47.515895500 | 2026-07-01T21:54:47.515895500
java.time of()   : 2026-07-01 | 14:30 | 2026-07-01T14:30
Joda      now()  : 2026-07-01 | 21:54:47.599 | 2026-07-01T21:54:47.602-03:00
Joda      of()   : 2026-07-01 | 14:30:00.000 | 2026-07-01T14:30:00.000-03:00

=== Time zones ===
java.time now(zone) : 2026-07-01T21:54:47.603767500-03:00[America/Sao_Paulo]
java.time of(zone)  : 2026-07-01T14:30-03:00[America/Sao_Paulo]
Joda      now(zone) : 2026-07-01T21:54:47.605-03:00
Joda      of(zone)  : 2026-07-01T14:30:00.000-03:00

=== Instants / epoch ===
java.time now()       : 2026-07-02T00:54:47.606766400Z
java.time ofEpochMilli: 1970-01-01T00:00:00Z
Joda      now()       : 2026-07-02T00:54:47.607Z
Joda      ofEpochMilli: 1970-01-01T00:00:00.000Z

=== Parsing & formatting ===
java.time parse("01/07/2026") : 2026-07-01
java.time format(...)       : 01/07/2026
Joda      parse("01/07/2026") : 2026-07-01
Joda      format(...)       : 01/07/2026

BUILD SUCCESSFUL in 3s
```

### Result Arithmetic & comparison

```
=== Adding/subtracting: Period & Duration ===
java.time plus Period(1y2m10d) : 2027-09-11
java.time minus Period(1y2m10d): 2025-04-21
Joda      plus Period(1y2m10d) : 2027-09-11
Joda      minus Period(1y2m10d): 2025-04-21
java.time plus Duration(3h30m) : 2026-07-01T18:00
Joda      plus Duration(3h30m) : 2026-07-01T18:00:00.000-03:00

=== Comparing dates ===
java.time isBefore/isAfter/compareTo : true / false / -5
Joda      isBefore/isAfter/compareTo : true / false / -1

=== Date ranges ===
java.time manual range check : true
Joda      Interval.contains  : true

BUILD SUCCESSFUL in 10s
```
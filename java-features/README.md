# java-features

POCs for features introduced in each Java version, from **Java 11** through **Java 26**.

Each POC should live in its own package under `src/main/java/com`, named after the feature (e.g. `com.records`, `com.virtualthreads`).

## Checklist by version

### Java 11 (LTS) — [JDK 11 release notes](https://www.oracle.com/java/technologies/javase/11-relnote-issues.html)

- [x] `var` in lambda parameters — [JEP 323](https://openjdk.org/jeps/323)
- [x] New `HttpClient` API (standard) — [JEP 321](https://openjdk.org/jeps/321)
- [x] Launch single-file source-code programs (`java Main.java`) — [JEP 330](https://openjdk.org/jeps/330)
- [ ] `String` convenience methods (`isBlank`, `strip`, `lines`, `repeat`)
- [ ] Epsilon: a no-op garbage collector — [JEP 318](https://openjdk.org/jeps/318)
- [ ] Flight Recorder — [JEP 328](https://openjdk.org/jeps/328)

### Java 12 — [JDK 12 release notes](https://www.oracle.com/java/technologies/javase/12-relnote-issues.html)

- [ ] Switch expressions (preview) — [JEP 325](https://openjdk.org/jeps/325)
- [ ] Compact number formatting (`NumberFormat`) — [JEP 326-related API](https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/text/CompactNumberFormat.html)
- [ ] Shenandoah: a low-pause-time GC (experimental) — [JEP 189](https://openjdk.org/jeps/189)
- [ ] Default CDS archives — [JEP 341](https://openjdk.org/jeps/341)

### Java 13 — [JDK 13 release notes](https://www.oracle.com/java/technologies/javase/13-relnote-issues.html)

- [ ] Text blocks (preview) — [JEP 355](https://openjdk.org/jeps/355)
- [ ] Switch expressions, 2nd preview (`yield`) — [JEP 354](https://openjdk.org/jeps/354)
- [ ] Dynamic CDS archives — [JEP 350](https://openjdk.org/jeps/350)

### Java 14 — [JDK 14 release notes](https://www.oracle.com/java/technologies/javase/14-relnote-issues.html)

- [ ] Switch expressions (standard) — [JEP 361](https://openjdk.org/jeps/361)
- [ ] Pattern matching for `instanceof` (preview) — [JEP 305](https://openjdk.org/jeps/305)
- [ ] Records (preview) — [JEP 359](https://openjdk.org/jeps/359)
- [ ] Helpful NullPointerExceptions — [JEP 358](https://openjdk.org/jeps/358)
- [ ] Text blocks, 2nd preview — [JEP 368](https://openjdk.org/jeps/368)

### Java 15 — [JDK 15 release notes](https://www.oracle.com/java/technologies/javase/15-relnote-issues.html)

- [ ] Text blocks (standard) — [JEP 378](https://openjdk.org/jeps/378)
- [ ] Sealed classes (preview) — [JEP 360](https://openjdk.org/jeps/360)
- [ ] Hidden classes — [JEP 371](https://openjdk.org/jeps/371)
- [ ] Records, 2nd preview — [JEP 384](https://openjdk.org/jeps/384)

### Java 16 — [JDK 16 release notes](https://www.oracle.com/java/technologies/javase/16-relnote-issues.html)

- [ ] Records (standard) — [JEP 395](https://openjdk.org/jeps/395)
- [ ] Pattern matching for `instanceof` (standard) — [JEP 394](https://openjdk.org/jeps/394)
- [ ] Sealed classes, 2nd preview — [JEP 397](https://openjdk.org/jeps/397)
- [ ] Vector API (incubator) — [JEP 338](https://openjdk.org/jeps/338)
- [ ] Foreign Linker API (incubator) — [JEP 389](https://openjdk.org/jeps/389)

### Java 17 (LTS) — [JDK 17 release notes](https://www.oracle.com/java/technologies/javase/17-relnote-issues.html)

- [ ] Sealed classes (standard) — [JEP 409](https://openjdk.org/jeps/409)
- [ ] Pattern matching for `switch` (preview) — [JEP 406](https://openjdk.org/jeps/406)
- [ ] Enhanced pseudo-random number generators — [JEP 356](https://openjdk.org/jeps/356)
- [ ] Foreign Function & Memory API (incubator) — [JEP 412](https://openjdk.org/jeps/412)
- [ ] Strong encapsulation of JDK internals by default — [JEP 403](https://openjdk.org/jeps/403)

### Java 18 — [JDK 18 release notes](https://www.oracle.com/java/technologies/javase/18-relnote-issues.html)

- [ ] UTF-8 by default — [JEP 400](https://openjdk.org/jeps/400)
- [ ] Simple web server (`jwebserver`) — [JEP 408](https://openjdk.org/jeps/408)
- [ ] Code snippets in Javadoc (`@snippet`) — [JEP 413](https://openjdk.org/jeps/413)
- [ ] Pattern matching for `switch`, 2nd preview — [JEP 420](https://openjdk.org/jeps/420)
- [ ] Deprecate finalization for removal — [JEP 421](https://openjdk.org/jeps/421)

### Java 19 — [JDK 19 release notes](https://www.oracle.com/java/technologies/javase/19-relnote-issues.html)

- [ ] Virtual threads (preview) — [JEP 425](https://openjdk.org/jeps/425)
- [ ] Structured concurrency (incubator) — [JEP 428](https://openjdk.org/jeps/428)
- [ ] Record patterns (preview) — [JEP 405](https://openjdk.org/jeps/405)
- [ ] Pattern matching for `switch`, 3rd preview — [JEP 427](https://openjdk.org/jeps/427)
- [ ] Foreign Function & Memory API, 2nd preview — [JEP 424](https://openjdk.org/jeps/424)

### Java 20 — [JDK 20 release notes](https://www.oracle.com/java/technologies/javase/20-relnote-issues.html)

- [ ] Scoped values (incubator) — [JEP 429](https://openjdk.org/jeps/429)
- [ ] Record patterns, 2nd preview — [JEP 432](https://openjdk.org/jeps/432)
- [ ] Virtual threads, 2nd preview — [JEP 436](https://openjdk.org/jeps/436)
- [ ] Structured concurrency, 2nd incubator — [JEP 437](https://openjdk.org/jeps/437)

### Java 21 (LTS) — [JDK 21 release notes](https://www.oracle.com/java/technologies/javase/21-relnote-issues.html)

- [ ] Virtual threads (standard) — [JEP 444](https://openjdk.org/jeps/444)
- [ ] Pattern matching for `switch` (standard) — [JEP 441](https://openjdk.org/jeps/441)
- [ ] Record patterns (standard) — [JEP 440](https://openjdk.org/jeps/440)
- [ ] Sequenced collections — [JEP 431](https://openjdk.org/jeps/431)
- [ ] Generational ZGC — [JEP 439](https://openjdk.org/jeps/439)
- [ ] String templates (preview) — [JEP 430](https://openjdk.org/jeps/430)
- [ ] Scoped values (preview) — [JEP 446](https://openjdk.org/jeps/446)
- [ ] Structured concurrency (preview) — [JEP 453](https://openjdk.org/jeps/453)
- [ ] Unnamed patterns and variables (preview) — [JEP 443](https://openjdk.org/jeps/443)

### Java 22 — [JDK 22 release notes](https://www.oracle.com/java/technologies/javase/22-relnote-issues.html)

- [ ] Statements before `super(...)` (preview) — [JEP 447](https://openjdk.org/jeps/447)
- [ ] Unnamed variables & patterns (standard) — [JEP 456](https://openjdk.org/jeps/456)
- [ ] Foreign Function & Memory API (standard) — [JEP 454](https://openjdk.org/jeps/454)
- [ ] Stream gatherers (preview) — [JEP 461](https://openjdk.org/jeps/461)
- [ ] String templates, 2nd preview — [JEP 459](https://openjdk.org/jeps/459)

### Java 23 — [JDK 23 release notes](https://www.oracle.com/java/technologies/javase/23-relnote-issues.html)

- [ ] Primitive types in patterns, `instanceof`, and `switch` (preview) — [JEP 455](https://openjdk.org/jeps/455)
- [ ] Markdown documentation comments (Javadoc) — [JEP 467](https://openjdk.org/jeps/467)
- [ ] Module import declarations (preview) — [JEP 476](https://openjdk.org/jeps/476)
- [ ] Class-File API, 2nd preview — [JEP 466](https://openjdk.org/jeps/466)
- [ ] Stream gatherers, 2nd preview — [JEP 473](https://openjdk.org/jeps/473)
- [ ] ZGC generational mode by default — [JEP 474](https://openjdk.org/jeps/474)

### Java 24 — [JDK 24 release notes](https://www.oracle.com/java/technologies/javase/24-relnote-issues.html)

- [ ] Stream gatherers (standard) — [JEP 485](https://openjdk.org/jeps/485)
- [ ] Class-File API (standard) — [JEP 484](https://openjdk.org/jeps/484)
- [ ] Ahead-of-time class loading & linking — [JEP 483](https://openjdk.org/jeps/483)
- [ ] Synchronize virtual threads without pinning — [JEP 491](https://openjdk.org/jeps/491)
- [ ] Compact object headers (experimental) — [JEP 450](https://openjdk.org/jeps/450)
- [ ] Quantum-resistant ML-KEM / ML-DSA — [JEP 496](https://openjdk.org/jeps/496) / [JEP 497](https://openjdk.org/jeps/497)

### Java 25 (LTS) — [JDK 25 release notes](https://www.oracle.com/java/technologies/javase/25-relnote-issues.html)

- [ ] Compact source files and instance main methods (standard) — [JEP 512](https://openjdk.org/jeps/512)
- [ ] Module import declarations (standard) — [JEP 511](https://openjdk.org/jeps/511)
- [ ] Flexible constructor bodies (standard) — [JEP 513](https://openjdk.org/jeps/513)
- [ ] Scoped values (standard) — [JEP 506](https://openjdk.org/jeps/506)
- [ ] Structured concurrency (standard) — [JEP 505](https://openjdk.org/jeps/505)
- [ ] Key Derivation Function API — [JEP 510](https://openjdk.org/jeps/510)
- [ ] Compact object headers (standard) — [JEP 519](https://openjdk.org/jeps/519)
- [ ] PEM encodings (preview) — [JEP 470](https://openjdk.org/jeps/470)

### Java 26 — [JDK 26 release notes](https://www.oracle.com/java/technologies/javase/26-relnote-issues.html)

- [ ] HTTP/3 support for the HTTP Client API — [JEP 517](https://openjdk.org/jeps/517)
- [ ] Primitive types in patterns, `instanceof`, and `switch`, 4th preview — [JEP 530](https://openjdk.org/jeps/530)
- [ ] Structured concurrency, 6th preview — [JEP 525](https://openjdk.org/jeps/525)
- [ ] Ahead-of-time object caching with any GC — [JEP 516](https://openjdk.org/jeps/516)
- [ ] G1 GC synchronization overhead reduction — [JEP 522](https://openjdk.org/jeps/522)
- [ ] Lazy constants, 2nd preview — [JEP 526](https://openjdk.org/jeps/526)

## Reference

- [JDK Enhancement Proposals (JEP) index](https://openjdk.org/jeps/0)
- [Oracle Java SE release notes archive](https://www.oracle.com/java/technologies/javase/release-notes-archive.html)

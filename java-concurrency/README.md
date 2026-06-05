## java-concurrency

Java 26 POC showing how to use `ExecutorService` and related concurrency tools applied to a `Task` model.

- `newFixedThreadPool` — runs a set number of threads concurrently
- `newSingleThreadExecutor` — runs tasks sequentially in a single thread
- `newCachedThreadPool` — creates new threads as needed, reuses idle ones
- `runWithRunnable` — executes a task in a thread without returning a result
- `runWithCallable` — like Runnable but returns a result and can throw checked exceptions
- `runWithFuture` — submits a task and retrieves its result when ready, with timeout support

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
=== Fixed Thread Pool (2 threads) ===
[pool-1-thread-2] RUNNING: Send Emails
[pool-1-thread-1] RUNNING: Database Backup
[pool-1-thread-2] DONE   : Send Emails
[pool-1-thread-1] DONE   : Database Backup
[pool-1-thread-1] RUNNING: Generate Report
[pool-1-thread-2] RUNNING: Clear Cache
[pool-1-thread-1] DONE   : Generate Report
[pool-1-thread-2] DONE   : Clear Cache

=== Single Thread ===
[pool-2-thread-1] RUNNING: Database Backup
[pool-2-thread-1] DONE   : Database Backup
[pool-2-thread-1] RUNNING: Send Emails
[pool-2-thread-1] DONE   : Send Emails
[pool-2-thread-1] RUNNING: Generate Report
[pool-2-thread-1] DONE   : Generate Report
[pool-2-thread-1] RUNNING: Clear Cache
[pool-2-thread-1] DONE   : Clear Cache

=== Cached Thread Pool ===
[pool-3-thread-2] RUNNING: Send Emails
[pool-3-thread-1] RUNNING: Database Backup
[pool-3-thread-4] RUNNING: Clear Cache
[pool-3-thread-3] RUNNING: Generate Report
[pool-3-thread-2] DONE   : Send Emails
[pool-3-thread-1] DONE   : Database Backup
[pool-3-thread-3] DONE   : Generate Report
[pool-3-thread-4] DONE   : Clear Cache

=== Runnable ===
[pool-4-thread-1] RUNNING: Send Notification
[pool-4-thread-1] DONE   : Send Notification

=== Callable ===
[pool-5-thread-1] RUNNING: Process Payment
Result of Process Payment

=== Future ===
[pool-6-thread-2] RUNNING: Send Emails
[pool-6-thread-1] RUNNING: Database Backup
[pool-6-thread-2] DONE   : Send Emails
[pool-6-thread-1] DONE   : Database Backup
[pool-6-thread-2] RUNNING: Generate Report
Result of Database Backup
Result of Send Emails
[pool-6-thread-1] RUNNING: Clear Cache
[pool-6-thread-2] DONE   : Generate Report
[pool-6-thread-1] DONE   : Clear Cache
Result of Generate Report
Result of Clear Cache

BUILD SUCCESSFUL in 5s
```
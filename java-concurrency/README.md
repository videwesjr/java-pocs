## java-concurrency

Java 26 POC showing how to use `ExecutorService`, locks, and atomic variables applied to a `Task` model.

### ExecutorService

- `newFixedThreadPool` — runs a set number of threads concurrently
- `newSingleThreadExecutor` — runs tasks sequentially in a single thread
- `newCachedThreadPool` — creates new threads as needed, reuses idle ones
- `runWithRunnable` — executes a task in a thread without returning a result
- `runWithCallable` — like Runnable but returns a result and can throw checked exceptions
- `runWithFuture` — submits a task and retrieves its result when ready, with timeout support

### Locks

- `runWithReentrantLock` — explicit lock manually acquired/released; ensures only one thread enters the critical section at a time
- `runWithReadWriteLock` — multiple concurrent readers or one exclusive writer; tasks with priority 1 write, others read
- `runWithStampedLock` — optimistic read attempts without acquiring a lock, validated afterward; falls back to a full read lock on conflict

### Atomics

- `runWithAtomicInteger` — lock-free thread-safe counter using CPU-level CAS operations
- `runWithAtomicBoolean` — thread-safe flag; only the first thread to CAS from `false→true` executes, the rest are skipped
- `runWithAtomicReference` — thread-safe reference to any object, updated concurrently
- `runWithLongAdder` — high-throughput counter under heavy contention; better than `AtomicInteger` when many threads write frequently

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

=== ReentrantLock ===
[pool-7-thread-1] RUNNING: Database Backup | counter: 1
[pool-7-thread-2] RUNNING: Send Emails | counter: 2
[pool-7-thread-3] RUNNING: Generate Report | counter: 3
[pool-7-thread-4] RUNNING: Clear Cache | counter: 4

=== ReadWriteLock ===
[pool-8-thread-1] WRITE: updated by Database Backup
[pool-8-thread-4] READ : updated by Database Backup | task: Clear Cache
[pool-8-thread-3] READ : updated by Database Backup | task: Generate Report
[pool-8-thread-2] READ : updated by Database Backup | task: Send Emails

=== StampedLock ===
[pool-9-thread-1] WRITE: 10.0 | task: Database Backup
[pool-9-thread-2] OPTIMISTIC READ: 10.0 | task: Send Emails
[pool-9-thread-3] OPTIMISTIC READ: 10.0 | task: Generate Report
[pool-9-thread-4] OPTIMISTIC READ: 10.0 | task: Clear Cache

=== AtomicInteger ===
[pool-10-thread-1] RUNNING: Database Backup | atomicCounter: 1
[pool-10-thread-2] RUNNING: Send Emails | atomicCounter: 2
[pool-10-thread-3] RUNNING: Generate Report | atomicCounter: 3
[pool-10-thread-4] RUNNING: Clear Cache | atomicCounter: 4
final atomicCounter: 4

=== AtomicBoolean ===
[pool-11-thread-1] FIRST TASK: Database Backup
[pool-11-thread-2] SKIPPED   : Send Emails
[pool-11-thread-3] SKIPPED   : Generate Report
[pool-11-thread-4] SKIPPED   : Clear Cache

=== AtomicReference ===
[pool-12-thread-1] SET: Database Backup
[pool-12-thread-2] SET: Send Emails
[pool-12-thread-3] SET: Generate Report
[pool-12-thread-4] SET: Clear Cache
final reference: Clear Cache

=== LongAdder ===
[pool-13-thread-2] RUNNING: Send Emails | longAdder: 4
[pool-13-thread-1] RUNNING: Database Backup | longAdder: 1
[pool-13-thread-3] RUNNING: Generate Report | longAdder: 2
[pool-13-thread-4] RUNNING: Clear Cache | longAdder: 3
final longAdder: 4
```
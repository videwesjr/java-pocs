import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class LocksHandler {
    // ReentrantLock - explicit lock that must be manually acquired and released
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private int sharedCounter = 0;

    public void runWithReentrantLock(List<Task> tasks) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (Task task : tasks) {
            executor.submit(() -> {
                reentrantLock.lock();
                try {
                    sharedCounter++;
                    System.out.println("[" + Thread.currentThread().getName() + "] RUNNING: " + task.name() + " | counter: " + sharedCounter);
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    reentrantLock.unlock();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    // ReadWriteLock - allows multiple readers or one exclusive writer at a time
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private String sharedData = "initial data";

    public void runWithReadWriteLock(List<Task> tasks) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (Task task : tasks) {
            if (task.priority() == 1) {
                executor.submit(() -> {
                    readWriteLock.writeLock().lock();
                    try {
                        sharedData = "updated by " + task.name();
                        System.out.println("[" + Thread.currentThread().getName() + "] WRITE: " + sharedData);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        readWriteLock.writeLock().unlock();
                    }
                });
            } else {
                executor.submit(() -> {
                    readWriteLock.readLock().lock();
                    try {
                        System.out.println("[" + Thread.currentThread().getName() + "] READ : " + sharedData + " | task: " + task.name());
                    } finally {
                        readWriteLock.readLock().unlock();
                    }
                });
            }
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    // StampedLock - optimistic read without acquiring a lock, validated afterward
    private final StampedLock stampedLock = new StampedLock();
    private double stampedData = 0.0;

    public void runWithStampedLock(List<Task> tasks) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (Task task : tasks) {
            if (task.priority() == 1) {
                executor.submit(() -> {
                    long stamp = stampedLock.writeLock();
                    try {
                        stampedData += 10.0;
                        System.out.println("[" + Thread.currentThread().getName() + "] WRITE: " + stampedData + " | task: " + task.name());
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        stampedLock.unlockWrite(stamp);
                    }
                });
            } else {
                executor.submit(() -> {
                    long stamp = stampedLock.tryOptimisticRead();
                    double value = stampedData;
                    if (!stampedLock.validate(stamp)) {
                        stamp = stampedLock.readLock();
                        try {
                            value = stampedData;
                        } finally {
                            stampedLock.unlockRead(stamp);
                        }
                    }
                    System.out.println("[" + Thread.currentThread().getName() + "] OPTIMISTIC READ: " + value + " | task: " + task.name());
                });
            }
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    // AtomicInteger - thread-safe counter using CPU-level CAS operations
    private final AtomicInteger atomicCounter = new AtomicInteger(0);

    public void runWithAtomicInteger(List<Task> tasks) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (Task task : tasks) {
            executor.submit(() -> {
                int value = atomicCounter.incrementAndGet();
                System.out.println("[" + Thread.currentThread().getName() + "] RUNNING: " + task.name() + " | atomicCounter: " + value);
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("final atomicCounter: " + atomicCounter.get());
    }

    // AtomicBoolean - thread-safe flag, useful for one-time actions or shutdown signals
    private final AtomicBoolean atomicFlag = new AtomicBoolean(false);

    public void runWithAtomicBoolean(List<Task> tasks) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (Task task : tasks) {
            executor.submit(() -> {
                if (atomicFlag.compareAndSet(false, true)) {
                    System.out.println("[" + Thread.currentThread().getName() + "] FIRST TASK: " + task.name());
                } else {
                    System.out.println("[" + Thread.currentThread().getName() + "] SKIPPED   : " + task.name());
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    // AtomicReference - thread-safe reference to any object
    private final AtomicReference<Task> atomicReference = new AtomicReference<>(null);

    public void runWithAtomicReference(List<Task> tasks) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (Task task : tasks) {
            executor.submit(() -> {
                atomicReference.set(task);
                System.out.println("[" + Thread.currentThread().getName() + "] SET: " + atomicReference.get().name());
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("final reference: " + atomicReference.get().name());
    }

    // LongAdder - high performance counter under heavy contention
    private final LongAdder longAdder = new LongAdder();

    public void runWithLongAdder(List<Task> tasks) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (Task task : tasks) {
            executor.submit(() -> {
                longAdder.increment();
                System.out.println("[" + Thread.currentThread().getName() + "] RUNNING: " + task.name() + " | longAdder: " + longAdder.sum());
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("final longAdder: " + longAdder.sum());
    }
}

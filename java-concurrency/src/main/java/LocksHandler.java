import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

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
}

import java.util.List;
import java.util.concurrent.*;

public class ConcurrencyHandler {

    // ExecutorService - fixed thread pool, runs a set number of threads concurrently
    public void runWithFixedPool(List<Task> tasks, int threads) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (Task task : tasks) {
            executor.submit(() -> {
                System.out.println("[" + Thread.currentThread().getName() + "] RUNNING: " + task.name());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("[" + Thread.currentThread().getName() + "] DONE   : " + task.name());
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    // ExecutorService - single thread pool, runs tasks sequentially one at a time
    public void runWithSingleThread(List<Task> tasks) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (Task task : tasks) {
            executor.submit(() -> {
                System.out.println("[" + Thread.currentThread().getName() + "] RUNNING: " + task.name());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("[" + Thread.currentThread().getName() + "] DONE   : " + task.name());
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    // ExecutorService - cached thread pool, creates new threads as needed and reuses idle ones
    public void runWithCachedPool(List<Task> tasks) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (Task task : tasks) {
            executor.submit(() -> {
                System.out.println("[" + Thread.currentThread().getName() + "] RUNNING: " + task.name());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("[" + Thread.currentThread().getName() + "] DONE   : " + task.name());
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    // Runnable - executes a task in a thread without returning a result
    public void runWithRunnable(Task task) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Runnable runnable = () -> {
            System.out.println("[" + Thread.currentThread().getName() + "] RUNNING: " + task.name());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[" + Thread.currentThread().getName() + "] DONE   : " + task.name());
        };

        executor.submit(runnable);
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
    }

    // Callable - like Runnable but returns a result and can throw checked exceptions
    public String runWithCallable(Task task) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            System.out.println("[" + Thread.currentThread().getName() + "] RUNNING: " + task.name());
            Thread.sleep(500);
            return "Result of " + task.name();
        };

        Future<String> future = executor.submit(callable);
        String result = future.get();
        executor.shutdown();
        return result;
    }

    // Future - submits a task and retrieves its result when ready, with timeout support
    public void runWithFuture(List<Task> tasks) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        List<Future<String>> futures = tasks.stream()
                .map(task -> executor.submit(() -> {
                    System.out.println("[" + Thread.currentThread().getName() + "] RUNNING: " + task.name());
                    Thread.sleep(300);
                    System.out.println("[" + Thread.currentThread().getName() + "] DONE   : " + task.name());
                    return "Result of " + task.name();
                }))
                .toList();

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get(5, TimeUnit.SECONDS));
            } catch (ExecutionException | TimeoutException e) {
                System.out.println("task failed: " + e.getMessage());
            }
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
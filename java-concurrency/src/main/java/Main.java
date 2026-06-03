import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        ConcurrencyHandler handler = new ConcurrencyHandler();

        List<Task> tasks = List.of(
                new Task("Database Backup", 1),
                new Task("Send Emails", 2),
                new Task("Generate Report", 3),
                new Task("Clear Cache", 4)
        );

        System.out.println("=== Fixed Thread Pool (2 threads) ===");
        handler.runWithFixedPool(tasks, 2);

        System.out.println("\n=== Single Thread ===");
        handler.runWithSingleThread(tasks);

        System.out.println("\n=== Cached Thread Pool ===");
        handler.runWithCachedPool(tasks);

        System.out.println("\n=== Runnable ===");
        handler.runWithRunnable(new Task("Send Notification", 1));

        System.out.println("\n=== Callable ===");
        String result = handler.runWithCallable(new Task("Process Payment", 1));
        System.out.println(result);

        System.out.println("\n=== Future ===");
        handler.runWithFuture(tasks);
    }
}
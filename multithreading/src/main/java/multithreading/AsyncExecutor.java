package multithreading;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AsyncExecutor
{
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public void execute(final Runnable task)
    {
        executorService.submit(() -> {
            System.out.println("Processing order in thread: " + Thread.currentThread().getName());
            task.run();
        });
    }

    public void stop()
    {
        executorService.shutdown();
    }
}

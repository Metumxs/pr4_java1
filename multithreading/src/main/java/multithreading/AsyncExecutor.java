package multithreading;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class AsyncExecutor
{
    //private static final int THREAD_POOL_SIZE = 25000;
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public void execute(final Runnable task)
    {
        executorService.submit(() -> {
            System.out.println("+++ Processing in thread: " + Thread.currentThread().getName());
            try
            {
                task.run();
            }
            catch (Exception e)
            {
                System.err.println("Error inside thread: " + e.getMessage());
            }
        });
    }

    public void stop()
    {
        executorService.shutdown();
        try
        {
            // Waiting for 60s max, until tasks are completed (blocking main thread)
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
            {
                System.err.println("Tasks took too long, forcing shutdown...");
                executorService.shutdownNow();
            }
        }
        catch (InterruptedException e)
        {
            // If awaiting was interrupted
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

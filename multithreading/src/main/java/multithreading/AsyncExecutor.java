package multithreading;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class AsyncExecutor
{
    private static final int THREAD_POOL_SIZE = 100;
    //private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public void execute(final Runnable task)
    {
        executorService.submit(() -> {
            System.out.println("Processing in thread: " + Thread.currentThread().getName());
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
        // 1. Перестаємо приймати нові задачі
        executorService.shutdown();
        try
        {
            // 2. Блокуємо головний потік і чекаємо, поки всі працівники закінчать
            // Чекаємо максимум 60 секунд.
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
            {
                // Якщо не встигли за 60 сек — примусово зупиняємо
                System.err.println("Tasks took too long, forcing shutdown...");
                executorService.shutdownNow();
            }
        }
        catch (InterruptedException e)
        {
            // Якщо хтось перервав очікування
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

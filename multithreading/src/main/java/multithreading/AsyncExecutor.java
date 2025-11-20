package multithreading;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncExecutor
{
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    // Створюємо пул потоків.
    // 4 - оптимально для більшості ноутбуків (або Runtime.getRuntime().availableProcessors())
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

    public void execute(final Runnable task) {
        // Ми не створюємо new Thread(). Ми кидаємо задачу в пул.
        executorService.submit(task);
        // Пул сам вирішить, який потік виконає цю задачу.
    }

    // Метод для закриття (обов'язково додай!)
    public void stop() {
        executorService.shutdown();
    }
}

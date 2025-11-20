package multithreading;


public class AsyncExecutor
{
    public void execute(final Runnable task)
    {
        final var thread = new Thread(task);

        System.out.println("Running a new thread: " + thread.getId());

        thread.start();
    }
}

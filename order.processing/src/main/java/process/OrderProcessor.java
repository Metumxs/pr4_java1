package process;


import order.Order;


public class OrderProcessor
{
    private final Order<?> order;

    public OrderProcessor(final Order<?> order)
    {
        this.order = order;
    }

    public void processOrder()
    {
        System.out.printf("Starting processing of the order [%s] for product [%s]...%n", order.getId(), order.getProduct().getId());

        try
        {
            // Simulate order processing time.
            Thread.sleep(500L);
        }
        catch (final InterruptedException e)
        {
            System.err.printf("Order processing was interrupted: %s%n", e.getMessage());
        }

        System.out.printf("Processing finished of the order [%s] for product [%s]%n", order.getId(), order.getProduct().getId());
    }
}

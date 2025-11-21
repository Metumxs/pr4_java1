package application;


import factory.ProductFactory;
import multithreading.AsyncExecutor;
import order.Order;
import process.OrderProcessor;
import repository.OrderRepository;

import java.util.stream.IntStream;


public class ApplicationMain
{
    private final static ProductFactory PRODUCT_FACTORY = new ProductFactory();
    private final static OrderRepository ORDER_REPOSITORY = new OrderRepository();
    private final static AsyncExecutor ASYNC_EXECUTOR = new AsyncExecutor();

    public static void main(final String[] args)
    {
        long startMainTime = System.currentTimeMillis();
        final int ORDERS_COUNT = 100;

        // Iterate ORDERS_COUNT times to create orders with different products.
        IntStream.range(0, ORDERS_COUNT)
                .mapToObj(number -> {
                    final var product = isEven(number)
                            ? PRODUCT_FACTORY.createElectronics()
                            : PRODUCT_FACTORY.createClothingArticle();

                    return Order.builder()
                            .id(Integer.toString(number))
                            .product(product)
                            .build();
                }).forEach(ORDER_REPOSITORY::saveOrder);

        // Processing all orders asynchronously.
        ORDER_REPOSITORY.findAllProducts()
                .stream()
                .map(OrderProcessor::new)
                .forEach(orderProcessor -> ASYNC_EXECUTOR.execute(orderProcessor::processOrder));

        ASYNC_EXECUTOR.stop();

        long endMainTime = System.currentTimeMillis();
        System.out.println("\n=======================================");
        System.out.println("All orders were processed in: " + (endMainTime - startMainTime) + " ms");
    }

    private static boolean isEven(final int i)
    {
        return i % 2 == 0;
    }
}

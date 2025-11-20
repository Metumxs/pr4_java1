package application;

import factory.ProductFactory;
import multithreading.AsyncExecutor;
import order.Order;
import process.OrderProcessor;
import repository.OrderRepository;

import java.util.stream.IntStream;


public class ApplicationMain
{
    private final static ProductFactory productFactory = new ProductFactory();
    private final static OrderRepository orderRepository = new OrderRepository();
    private final static AsyncExecutor asyncExecutory = new AsyncExecutor();

    public static void main(final String[] args)
    {
        // Iterate 100 times to create orders with different products.
        IntStream.range(0, 100)
                .mapToObj(number -> {
                    final var product = isEven(number)
                            ? productFactory.createElectronics()
                            : productFactory.createClothingArticle();

                    return Order.builder()
                            .id(Integer.toString(number))
                            .product(product)
                            .build();
                }).forEach(orderRepository::saveOrder);

        // Process all orders asynchronously.
        orderRepository.findAllProducts()
                .stream()
                .map(OrderProcessor::new)
                .forEach(orderProcessor -> asyncExecutory.execute(orderProcessor::processOrder));

    }

    private static boolean isEven(final int i)
    {
        return i % 2 == 0;
    }
}

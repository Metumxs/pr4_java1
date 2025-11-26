package repository;


import order.Order;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;


public class OrderRepository
{
    private final ConcurrentHashMap<String, Order<?>> orderStorage = new ConcurrentHashMap<>();

    public void saveOrder(Order<?> order)
    {
        orderStorage.put(order.getId(), order);
    }

    public Order<?> findProductById(String id)
    {
        return orderStorage.get(id);
    }

    public Collection<Order<?>> findAllProducts()
    {
        return orderStorage.values();
    }
}

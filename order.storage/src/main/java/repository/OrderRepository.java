package repository;


import order.Order;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;


public class OrderRepository
{
    private final ConcurrentHashMap<String, Order<?>> storage = new ConcurrentHashMap<>();

    public void saveOrder(Order<?> order)
    {
        storage.put(order.getId(), order);
    }

    public Order<?> findProductById(String id)
    {
        return storage.get(id);
    }

    public Collection<Order<?>> findAllProducts()
    {
        return storage.values();
    }
}

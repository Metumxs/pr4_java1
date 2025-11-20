package order;

import lombok.Builder;
import lombok.Getter;
import products.Product;


@Builder
@Getter
public class Order<T extends Product>
{
    private final String id;
    private final T product;
}

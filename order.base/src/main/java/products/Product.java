package products;

import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
public abstract class Product
{
    private final String id;
    private final String description;


    protected Product(final String id, final String description)
    {
        this.id = id;
        this.description = description;
    }
}

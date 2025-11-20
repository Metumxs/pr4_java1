package products;

import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
public class ClothingArticle extends Product
{
    private final String type;
    private final String brand;
    private final String model;

    protected ClothingArticle(
            final String id,
            final String description,
            final String type,
            final String brand,
            final String model)
    {
        super(id, description);
        this.type = type;
        this.brand = brand;
        this.model = model;
    }
}

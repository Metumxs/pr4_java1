package products;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder
public class Electronics extends Product
{
    private final String appliance;
    private final String issuer;
    private final String model;

    protected Electronics(
            final String id,
            final String description,
            final String appliance,
            final String issuer,
            final String model)
    {
        super(id, description);
        this.appliance = appliance;
        this.issuer = issuer;
        this.model = model;
    }
}

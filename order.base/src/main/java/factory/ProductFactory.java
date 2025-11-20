package factory;

import com.github.javafaker.Faker;
import products.ClothingArticle;
import products.Electronics;


public class ProductFactory
{
    private final Faker faker = new Faker();

    public ClothingArticle createClothingArticle()
    {
        return ClothingArticle.builder()
                .id(faker.idNumber().valid() + "-clothing-article")
                .description(faker.lorem().sentence())
                .type(faker.commerce().productName())
                .brand(faker.company().name())
                .model(faker.bothify("??-####"))
                .build();
    }

    public Electronics createElectronics()
    {
        return Electronics.builder()
                .id(faker.idNumber().valid() + "-electronics")
                .description(faker.lorem().sentence())
                .appliance(faker.commerce().productName())
                .issuer(faker.company().name())
                .model(faker.bothify("??-####"))
                .build();
    }
}

/**
 * product items: Burger, GarlicBread
 * product type: basic, standard, prime
 * styles: normal, healthy
 * 
 */

/**
 * ASSUMPTION:
 * Burger are healthy, Garlic Bread are normal; Vice-Versa is not true.
 * 
 * So we will have separate factories for each item type.
 * otherwise, we will be having (m*n*o) factories for
 * -> m items
 * -> n types
 * -> o styles
 */

// styles (Strategy)
interface ProductStyle {
    void applyStyle();
}

interface ProductType {
    void applyType();
}

interface Item {
    void applyItem();

    ProductStyle getDefaultStyle();
}

interface Product {
    void createProduct();
}

interface ProductFactory {
    Product createProduct(ProductType productType, Item item);
}

class NormalStyle implements ProductStyle {
    @Override
    public void applyStyle() {
        System.out.println("Applying Normal Style");
    }
}

class HealthyStyle implements ProductStyle {
    @Override
    public void applyStyle() {
        System.out.println("Applying Healthy Style");
    }
}

// types (strategy)

class BasicType implements ProductType {
    @Override
    public void applyType() {
        System.out.println("Applying Basic Type");
    }
}

class StandardType implements ProductType {
    @Override
    public void applyType() {
        System.out.println("Applying Standard Type");
    }
}

class PrimeType implements ProductType {
    @Override
    public void applyType() {
        System.out.println("Applying Standard Type");
    }
}

// items (Strategy)

class BurgerItem implements Item {
    @Override
    public void applyItem() {
        System.out.println("Applying Burger Item");
    }

    @Override
    public ProductStyle getDefaultStyle() {
        return new HealthyStyle();
    }
}

class GarlicBreadItem implements Item {
    @Override
    public void applyItem() {
        System.out.println("Applying Garlic Bread Item");
    }

    @Override
    public ProductStyle getDefaultStyle() {
        return new NormalStyle();
    }
}

// main product combining all strategies
abstract class AbstractProduct implements Product {
    Item item;
    ProductType productType;
    ProductStyle productStyle;

    public AbstractProduct(Item item, ProductType productType, ProductStyle productStyle) {
        this.item = item;
        this.productType = productType;
        this.productStyle = productStyle;
    }
}

class ConcreteProduct extends AbstractProduct {
    public ConcreteProduct(Item item, ProductType productType, ProductStyle productStyle) {
        super(item, productType, productStyle);
    }

    @Override
    public void createProduct() {
        item.applyItem();
        productType.applyType();
        productStyle.applyStyle();
        System.out.println("Food Product Created with above configurations.");
    }

}

interface ProductFactory {
    Product createProduct(ProductType productType, Item item);
}

// product creating factory should be "Sigleton"
class GeneralProductFactory implements ProductFactory {

    private static GeneralProductFactory instance;

    private GeneralProductFactory() {
    }

    public static GeneralProductFactory getInstance() {
        if (instance == null) {
            synchronized (GeneralProductFactory.class) {
                if (instance == null) {
                    instance = new GeneralProductFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public Product createProduct(ProductType productType, Item item) {
        ProductStyle productStyle = item.getDefaultStyle();
        return new ConcreteProduct(item, productType, productStyle);
    }
}

///////////////////////////////////////////////////////////////
public class CombineStrategyFactory {

    public static void main(String[] args) {
        //// without using factory ////
        // Example: Create a Burger with Basic Type and Healthy Style
        Product basicHealthyBurger = new ConcreteProduct(
                new BurgerItem(),
                new BasicType(),
                new NormalStyle());
        basicHealthyBurger.createProduct();

        System.out.println("-----");

        // Example: Create Garlic Bread with Standard Type and Normal Style
        Product standardNormalGarlicBread = new ConcreteProduct(
                new GarlicBreadItem(),
                new StandardType(),
                new HealthyStyle());
        standardNormalGarlicBread.createProduct();

        System.out.println("-- using factory to create products --");

        //// using factory (legacy) ////
        ProductFactory productFactory = GeneralProductFactory.getInstance();
        /// Example: Create a Healthy Burger with Standard Type
        Product standardBurger = productFactory.createProduct(new StandardType(), new BurgerItem());
        standardBurger.createProduct();
    }
}
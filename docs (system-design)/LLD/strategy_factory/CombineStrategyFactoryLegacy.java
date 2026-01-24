
/**
 * product items: Burger, GarlicBread
 * product type: basic, standard, prime
 * styles: normal, healthy
 * 
 */

// items (Strategy)
interface Item {
    void applyItem();
}

class BurgerItem implements Item {
    @Override
    public void applyItem() {
        System.out.println("Applying Burger Item");
    }
}

class GarlicBreadItem implements Item {
    @Override
    public void applyItem() {
        System.out.println("Applying Garlic Bread Item");
    }
}

// styles (Strategy)
interface ProductStyle {
    void applyStyle();
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
interface ProductType {
    void applyType();
}

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

// main product combining all strategies
interface Product {
    void createProduct();
}

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

/*
 * now client can directly create products at run-time passing the different
 * types
 */
////// now factories will be there to create combinations //////
interface ProductFactory {
    Product createProduct();
}

abstract class AbstractProductFactory implements ProductFactory {
    Item item;
    ProductType productType;
    ProductStyle productStyle;

    public AbstractProductFactory(Item item, ProductType productType, ProductStyle productStyle) {
        this.item = item;
        this.productType = productType;
        this.productStyle = productStyle;
    }
}

// Legacy factories for each combination
class HealthyBurgerFactory extends AbstractProductFactory {

    public HealthyBurgerFactory(ProductType productType) {
        super(new BurgerItem(), productType, new HealthyStyle());
    }

    @Override
    public Product createProduct() {
        return new ConcreteProduct(item, productType, productStyle);
    }
}

class NormalGarlicBreadFactory extends AbstractProductFactory {

    public NormalGarlicBreadFactory(ProductType productType) {
        super(new GarlicBreadItem(), productType, new NormalStyle());
    }

    @Override
    public Product createProduct() {
        return new ConcreteProduct(item, productType, productStyle);
    }
}

///////////////////////////////////////////////////////////////
public class CombineStrategyFactoryLegacy {

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

        System.out.println("-- using factory (legacy) to create products --");

        //// using factory (legacy) ////
        /// Example: Create a Healthy Burger with Standard Type
        ProductFactory healthyBurgerFactory = new HealthyBurgerFactory(new StandardType());
        Product healthyBurger = healthyBurgerFactory.createProduct();
        healthyBurger.createProduct();
    }
}
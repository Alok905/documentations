import java.util.ArrayList;
import java.util.List;

class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

interface Cart {
}

class ShoppingCart implements Cart {
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }
}

class ShoppintCartPrinter {
    private ShoppingCart cart;

    public ShoppintCartPrinter(ShoppingCart cart) {
        this.cart = cart;
    }

    public void printReceipt() {
        System.out.println("Receipt:");
        for (Item item : cart.getItems()) {
            System.out.println("- " + item.getPrice());
        }
        System.out.println("Total: " + cart.calculateTotal());
    }
}

interface ShoppingCartStorage {
    void saveCart(ShoppingCart cart);
}

class ShoppingCartStorageMongoDB implements ShoppingCartStorage {

    public void saveCart(ShoppingCart cart) {
        System.out.println("Cart saved in MongoDB.");
    }
}

class ShoppingCartStorageMySQL implements ShoppingCartStorage {

    public void saveCart(ShoppingCart cart) {
        System.out.println("Cart saved in MySQL.");
    }
}

public class OCP {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("Book", 12.99));
        cart.addItem(new Item("Pen", 1.49));

        ShoppintCartPrinter printer = new ShoppintCartPrinter(cart);
        printer.printReceipt();

        ShoppingCartStorage storage = new ShoppingCartStorageMongoDB();
        storage.saveCart(cart);

        ShoppingCartStorage storageMySQL = new ShoppingCartStorageMySQL();
        storageMySQL.saveCart(cart);
    }

}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Store {
    static Store singleObject;
    ShoppingCart activeShoppingCart = new ShoppingCart();
    ArrayList<Order> activeOrders = new ArrayList<Order>();
    ArrayList<Order> finishedOrders =new ArrayList<Order>();
    Map<String, Product> products = new HashMap<>();
    // HashMap<Product, Integer> stock = new HashMap<Product, Integer>();
    public static Store getInstance(){
        if(singleObject == null){
            singleObject = new Store();
        }
        return singleObject;
    }
    // CRUD
    Product createProduct(String productName, double price, String productDescription, int numberOfItemsInStock) {
        if (this.products.containsKey(productName)) {
            return this.products.get(productName);
        }
        else {
            Product p = new Product(productName, price, productDescription, numberOfItemsInStock);
            this.products.put(productName, p);
            return p;
        }
    }
    Product getProductWithName() {
        // if product with name exist, return the product
        // otherwise the product
        return null;
    }

    private Store(){

    }

    private void initializeProducts() {
        createProduct("GlowLite LED Desk Lamp", 39.50, "Adjustable brightness with touch control and USB charging.", 75);                                       createProduct("PureSip Water Bottle", 19.99, "BPA-free, insulated bottle keeps drinks cold for 24 hours.", 200);
        createProduct("ComfyCloud Memory Foam Pillow", 45.00, "Ergonomic pillow designed for neck and spine support.", 60);
        createProduct("TurboBlend Blender Pro", 149.99, "High-speed blender with multiple settings for smoothies and soups.", 40);
        createProduct("AuraSound Wireless Headphones", 99.95, "Noise-canceling over-ear headphones with long battery life.", 85);
        createProduct("FlexiCook Nonstick Pan", 29.99, "Durable nonstick coating, safe for all stovetops.", 150);
        createProduct("ZenGarden Indoor Plant Kit", 24.50, "Complete kit with seeds,soil, and pots for easy gardening.", 100);
        createProduct("TravelMate Carry-On Suitcase", 129.00, "Lightweight, durablesuitcase with 360-degree spinner wheels.", 30);
    }
    private void simulateShopper() {
        this.activeShoppingCart.addToCart("ComfyCloud Memory Foam Pillow", 5);

    }
    public static void displayShoppingMenu() {
        System.out.println("1. List products");
        System.out.println("2. Add to cart");
        System.out.println("3. Show cart");
    }

    public void startShopping (){
        initializeProducts();
        simulateShopper();
        Scanner scanner = new Scanner(System.in);
        boolean quitMainLoop = false;
        while (!quitMainLoop) {
            displayShoppingMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Rensa newline

            switch (choice) {
                case 1 -> {
                    System.out.println("Products:");
                    for (Product p : products.values()) {
                        System.out.println(p.toString());
                    }
                }
                case 3 -> {
                    System.out.println("Cart contents: ");
                    activeShoppingCart.showCart();
                }
                case 0 -> {
                    quitMainLoop = true;
                }
            }
        }
        scanner.close();
    }

}

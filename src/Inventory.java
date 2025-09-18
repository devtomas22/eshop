import java.lang.classfile.constantpool.InvokeDynamicEntry;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    Map<String, Product> products = new HashMap<>();
    static Inventory singleObject;

    public Map<String, Product> getProductsInInventory() {
        return Collections.unmodifiableMap(this.products);
    }

    private Product createProduct(String productName, double price, String productDescription, int numberOfItemsInStock) {
        if (this.products.containsKey(productName)) {
            return this.products.get(productName);
        }
        else {
            Product p = new Product(productName, price, productDescription, numberOfItemsInStock);
            this.products.put(productName, p);
            return p;
        }
    }
    
    public Product getProductWithName(String productName) {
        return this.products.get(productName);
    }

    public void initializeProducts() {
        createProduct("GlowLite LED Desk Lamp", 39.50, "Adjustable brightness with touch control and USB charging.", 75);
        createProduct("PureSip Water Bottle", 19.99, "BPA-free, insulated bottle keeps drinks cold for 24 hours.", 200);
        createProduct("ComfyCloud Memory Foam Pillow", 45.00, "Ergonomic pillow designed for neck and spine support.", 60);
        createProduct("TurboBlend Blender Pro", 149.99, "High-speed blender with multiple settings for smoothies and soups.", 40);
        createProduct("AuraSound Wireless Headphones", 99.95, "Noise-canceling over-ear headphones with long battery life.", 85);
        createProduct("FlexiCook Nonstick Pan", 29.99, "Durable nonstick coating, safe for all stovetops.", 150);
        createProduct("ZenGarden Indoor Plant Kit", 24.50, "Complete kit with seeds,soil, and pots for easy gardening.", 100);
        createProduct("TravelMate Carry-On Suitcase", 129.00, "Lightweight, durable suitcase with 360-degree spinner wheels.", 0);
    }

    public void printProductList() {
        System.out.println("Products:");
        for (Product p : products.values()) {
            System.out.println(p.toString());
        }
    }

    public static Inventory getInstance() {
        if (singleObject == null) {
            singleObject = new Inventory();
        }
        return singleObject;
    }

    public Map<String, Integer> collectAvailableProducts(Map<String, Integer> shoppingCart){
        Map<String, Integer> availableSubset = new HashMap<>();
        for (String productName : shoppingCart.keySet()){
            int numInStock = products.get(productName).getNumberOfItemsInStock();
            int numInCart = shoppingCart.get(productName);
            if (numInStock <= 0) {
                System.out.printf("%s is out of stock, did not add the item to the order.%n", productName);
            } else if (numInStock < numInCart){
                availableSubset.put(productName, numInStock);
                products.get(productName).setNumberOfItemsInStock(0);
                System.out.printf("Insufficient %s in stock, added remaining %d to order.%n");
            } else {
                availableSubset.put(productName, shoppingCart.get(productName));
                products.get(productName).setNumberOfItemsInStock(numInStock - numInCart);
            }
        }
        return availableSubset;
    }
}

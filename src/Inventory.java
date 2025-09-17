import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    Map<String, Product> products = new HashMap<>();

    public Map<Product> getProductsInInventory() {
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
        createProduct("GlowLite LED Desk Lamp", 39.50, "Adjustable brightness with touch control and USB charging.", 75);                                       createProduct("PureSip Water Bottle", 19.99, "BPA-free, insulated bottle keeps drinks cold for 24 hours.", 200);
        createProduct("ComfyCloud Memory Foam Pillow", 45.00, "Ergonomic pillow designed for neck and spine support.", 60);
        createProduct("TurboBlend Blender Pro", 149.99, "High-speed blender with multiple settings for smoothies and soups.", 40);
        createProduct("AuraSound Wireless Headphones", 99.95, "Noise-canceling over-ear headphones with long battery life.", 85);
        createProduct("FlexiCook Nonstick Pan", 29.99, "Durable nonstick coating, safe for all stovetops.", 150);
        createProduct("ZenGarden Indoor Plant Kit", 24.50, "Complete kit with seeds,soil, and pots for easy gardening.", 100);
        createProduct("TravelMate Carry-On Suitcase", 129.00, "Lightweight, durablesuitcase with 360-degree spinner wheels.", 30);
    }

    public void printProductList() {
        System.out.println("Products:");
        for (Product p : products.values()) {
            System.out.println(p.toString());
        }
    }

}

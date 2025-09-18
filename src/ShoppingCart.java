import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private final Map<String, Integer> products = new HashMap<>();

    public int addToCart(String productName, int count) {
        if (!products.containsKey(productName)) {
            products.put(productName, count);
        }
        else {
            products.put(productName, products.get(productName) + count);
        }
        return count;
    }

    public double getTotalCost() {
        double sum = 0;
        for (String key : products.keySet()) {
            sum += costForProduct(key);
        }
        return sum;
    }

    private double costForProduct(String productName) {
        Product product = Inventory.getInstance().getProductWithName(productName);
        double totalCost = 0;
        if (products.containsKey(productName)) {
            int count = products.get(productName);
            totalCost += product.getPrice() * count;
        }
        return totalCost;
    }

    public int addToCart(String productName) {
        return addToCart(productName, 1);
    }

    public void showCart() {
        products.forEach((productName, count) -> {
            double itemCost = Inventory.getInstance().getProductWithName(productName).getPrice();
            System.out.printf("Product: %30s ", productName);
            if (Inventory.getInstance().getProductWithName(productName).getNumberOfItemsInStock() <= 0){
                System.out.printf("OUT OF STOCK%n");
            } else {
                System.out.printf("%6.2f€ x %2d = %6.2f€%n", itemCost, count, itemCost * count);
            }
        });

        System.out.printf("Total: %.2f€%n", getTotalCost());
    }

    // TODO: Remove from cart
    public void removeFromCart(String productName, int count) {
        if(products.containsKey(productName)) {
            int countInCart = products.get(productName);

            if(countInCart <= count){
                products.remove(productName);
            }
            else {
                products.put(productName, countInCart - count);
            }
        }
    }

    public Map<String, Integer> getProducts() {
        return new HashMap<String,Integer>(products);
    }
}

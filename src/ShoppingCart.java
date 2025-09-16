import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    private Map<String, Integer> products = new HashMap<>();

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
        Product product = Store.getInstance().getProductWithName(productName);
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
        products.forEach((productName, count)
                -> System.out.printf("Product: %s x %d%n", productName, count));
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

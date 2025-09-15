import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    // ArrayList<Product> products = new ArrayList<Product>();
    Map<String, Integer> products = new HashMap<>();
    int addToCart(String productName, int count) {
        if (!products.containsKey(productName)) {
            products.put(productName, count);
        }
        else {
            products.put(productName, products.get(productName) + count);
        }
        return count;
    }
    int addToCart(String productName) {
        return addToCart(productName, 1);
    }

    // TODO: Remove from cart
}

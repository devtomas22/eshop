import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
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

    void showCart() {
        products.forEach((productName, count)
                -> System.out.printf("Product: %s x %d%n", productName, count));
    }

    // TODO: Remove from cart
    void removeFromCart(String productName, int count) {
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
}

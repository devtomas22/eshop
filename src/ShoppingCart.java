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

    void showCart() {
        products.forEach((productName, count) -> {
            System.out.printf("Product: %s x %d%n", productName, count);
        } );
    }

    void removeFromCart(String productName, int count) {
        if(products.containsKey(productName)) {
            if(products.get(productName) <= count){
                products.remove(productName);
            }
            else {
                products.compute(productName, (k, countInCart) -> countInCart - count);
            }
        }
    }
       // TODO: Remove from cart
}

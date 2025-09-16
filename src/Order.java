import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<String, Integer> orderedProducts = new HashMap<>();
    private Shipping shipping = null;
    private Customer customer;
    private Payment payment;

    private int orderID;
    public static int NextOrderID = 0;

    public Order(Customer customer, Shipping shipping, Payment payment, Map <String, Integer> orderedProducts) {
        this.orderID = NextOrderID++;
        this.customer = customer;
        this.shipping = shipping;
        this.payment = payment;
        this.orderedProducts = orderedProducts;
    }

    public Map<String, Integer> getOrderedProducts() {
        return new HashMap<String, Integer>(orderedProducts);
    }

    public Shipping getShipping() {
        return shipping;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Payment getPayment() {
        return payment;
    }

    public int getOrderID() {
        return orderID;
    }
}

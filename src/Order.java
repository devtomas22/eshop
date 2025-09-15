import java.util.ArrayList;

public class Order {
    ArrayList<Product> orderedProducts = new ArrayList<Product>();
    Shipping shipping = null;
    int orderID;
    Customer customer;
    Payment payment;
}

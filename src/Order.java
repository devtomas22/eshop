import java.util.ArrayList;

public class Order {
    ArrayList<Product> orderedProducts = new ArrayList<Product>();
    Shipping shipping = null;
    Customer customer;
    Payment payment;

    int orderID;
    public static int NextOrderID = 0;

    Order(Customer customer, Shipping shipping, Payment payment) {
        this.orderID = NextOrderID++;
        this.customer = customer;
        this.shipping = shipping;
        this.payment = payment;
    }
}

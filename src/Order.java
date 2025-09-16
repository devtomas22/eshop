import java.util.ArrayList;

public class Order {
    private ArrayList<String> orderedProducts = new ArrayList<String>();
    private Shipping shipping = null;
    private Customer customer;
    private Payment payment;

    int orderID;
    public static int NextOrderID = 0;

    public Order(Customer customer, Shipping shipping, Payment payment) {
        this.orderID = NextOrderID++;
        this.customer = customer;
        this.shipping = shipping;
        this.payment = payment;
    }

    public ArrayList<String> getOrderedProducts() {
        return new ArrayList<>(orderedProducts);
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

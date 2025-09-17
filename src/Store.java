import java.util.*;
import java.util.concurrent.Callable;

public class Store {
    static Store singleObject;
    ShoppingCart activeShoppingCart = new ShoppingCart();
    ArrayList<Order> activeOrders = new ArrayList<Order>();
    ArrayList<Order> finishedOrders =new ArrayList<Order>();
    // Map<String, Product> products = new HashMap<>();
    Map<Integer, Customer> customers = new HashMap<>();
    Customer activeCustomer;

    private Store() {}
    // HashMap<Product, Integer> stock = new HashMap<Product, Integer>();
    boolean registerCustomer(Customer customer) {
        if (this.customers.containsKey(customer.getCustomerID())) {
            activeCustomer = this.customers.get(customer.getCustomerID());
            return false;
        }
        else {
            this.customers.put(customer.getCustomerID(), customer);
            activeCustomer = customer;
            return true;
        }
    }

    private void simulateShopper() {
        Customer customer = new Customer("Sven", "Karlsson", "Näktergalsvägen", "12345", "sven@oracle.se");
        this.registerCustomer(customer);
        this.activeShoppingCart.addToCart("ComfyCloud Memory Foam Pillow", 5);
    }

    private void printCustomers() {
        for (Customer c : customers.values()) {
            System.out.println(c.toString());
        }
    }

    private void printCart() {
        System.out.println("Cart contents: ");
        activeShoppingCart.showCart();
    }
    private void checkoutCart(Scanner scanner) {
        Customer customer = null;
        if (this.activeCustomer != null) {
            System.out.println("Please verify whether this customer is you: yes/no");
            System.out.println(this.activeCustomer.toString());
            String choice = scanner.nextLine().trim();
            if (choice.equals("yes")) {
                customer = this.activeCustomer;
            }
        }
        if (customer == null) {
            this.readInputAndAddCustomer(scanner);
            customer = this.activeCustomer;
        }

        System.out.println("Choose a delivery method:");

        Map<String, Callable<Shipping>> menu = new HashMap<>();
        menu.put("Home Delivery", () -> { return new Shipping(Shipping.DeliveryOptions.HomeDelivery); });
        menu.put("Standard Delivery", () -> { return new Shipping(Shipping.DeliveryOptions.StandardDelivery); });
        Shipping shipping = MenuRunner.runMenuType(scanner, menu);

        Map<String, Callable<Payment>> paymentMenu = new HashMap<>();
        paymentMenu.put("Cash", () -> new Payment(Payment.PaymentMethod.CreditCard, activeShoppingCart.getTotalCost()));
        paymentMenu.put("Credit Card", () -> { return new Payment(Payment.PaymentMethod.CreditCard, activeShoppingCart.getTotalCost()); });
        Payment payment = MenuRunner.runMenuType(scanner, paymentMenu);

        Order order = new Order(customer, shipping, payment, activeShoppingCart.getProducts());

        this.activeShoppingCart = new ShoppingCart();
        this.activeOrders.add(order);
    }
    private boolean readInputAndAddCustomer(Scanner scanner) {
        System.out.println("Enter First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter Address:");
        String address = scanner.nextLine();
        System.out.println("Enter Phone Number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();
        return this.registerCustomer(new Customer(firstName, lastName, address, phoneNumber, email));
    }
    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Runnable> menu = new HashMap<>();
        menu.put("List products", () -> Inventory.getInstance().printProductList());
        menu.put("Show cart", () -> printCart());
        menu.put("Checkout cart", () -> checkoutCart(scanner));
        menu.put("Manage customers", () -> {
            Map<String, Runnable> submenu = new HashMap<>();
            submenu.put("Add customer", () -> readInputAndAddCustomer(scanner));
            submenu.put("List customers", () -> printCustomers());
            MenuRunner.runMenuUntilQuit(scanner, submenu);
        });
        MenuRunner.runMenuUntilQuit(scanner, menu);
        scanner.close();
    }

    public void startShopping (){
        Inventory.getInstance().initializeProducts();
        simulateShopper();
        runMenu();
    }

    public static Store getInstance(){
        if(singleObject == null){
            singleObject = new Store();
        }
        return singleObject;
    }
}

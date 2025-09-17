import java.util.*;
import java.util.concurrent.Callable;

public class Store {
    static Store singleObject;
    ShoppingCart activeShoppingCart = new ShoppingCart();
    ArrayList<Order> activeOrders = new ArrayList<Order>();
    ArrayList<Order> finishedOrders =new ArrayList<Order>();
    Map<String, Product> products = new HashMap<>();
    Map<Integer, Customer> customers = new HashMap<>();
    Customer activeCustomer;

    // HashMap<Product, Integer> stock = new HashMap<Product, Integer>();

    void convertShoppingCartToOrder() {
        Shipping shipping = new Shipping(Shipping.DeliveryOptions.HomeDelivery);
        Payment payment = new Payment(Payment.PaymentMethod.CreditCard, activeShoppingCart.getTotalCost());
        Customer customer = new Customer("Sven", "Karlsson", "Näktergalsvägen", "12345", "sven@oracle.se");
        Order order = new Order(customer, shipping, payment, activeShoppingCart.getProducts());

        this.activeShoppingCart = new ShoppingCart();
        this.activeOrders.add(order);
    }

    Product createProduct(String productName, double price, String productDescription, int numberOfItemsInStock) {
        if (this.products.containsKey(productName)) {
            return this.products.get(productName);
        }
        else {
            Product p = new Product(productName, price, productDescription, numberOfItemsInStock);
            this.products.put(productName, p);
            return p;
        }
    }
    Product getProductWithName(String productName) {
        return this.products.get(productName);
    }

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

    private Store(){

    }

    private void initializeProducts() {
        createProduct("GlowLite LED Desk Lamp", 39.50, "Adjustable brightness with touch control and USB charging.", 75);                                       createProduct("PureSip Water Bottle", 19.99, "BPA-free, insulated bottle keeps drinks cold for 24 hours.", 200);
        createProduct("ComfyCloud Memory Foam Pillow", 45.00, "Ergonomic pillow designed for neck and spine support.", 60);
        createProduct("TurboBlend Blender Pro", 149.99, "High-speed blender with multiple settings for smoothies and soups.", 40);
        createProduct("AuraSound Wireless Headphones", 99.95, "Noise-canceling over-ear headphones with long battery life.", 85);
        createProduct("FlexiCook Nonstick Pan", 29.99, "Durable nonstick coating, safe for all stovetops.", 150);
        createProduct("ZenGarden Indoor Plant Kit", 24.50, "Complete kit with seeds,soil, and pots for easy gardening.", 100);
        createProduct("TravelMate Carry-On Suitcase", 129.00, "Lightweight, durablesuitcase with 360-degree spinner wheels.", 30);
    }
    private void simulateShopper() {
        Customer customer = new Customer("Sven", "Karlsson", "Näktergalsvägen", "12345", "sven@oracle.se");
        this.registerCustomer(customer);
        this.activeShoppingCart.addToCart("ComfyCloud Memory Foam Pillow", 5);
    }

    public String GetChoice(Scanner scanner, List<String> list) {
        HashMap<Integer, String> choices = new  HashMap<>();
        for (int i= 0; i < list.size(); i++) {
            System.out.printf("%d: %s %n", i + 1, list.get(i));
            choices.put(i + 1, list.get(i));
        }
        System.out.println("Enter your choice: ");
        String selected = null;
        while (true) {
            try {
                String line = scanner.nextLine();
                int index = Integer.parseInt(line);
                selected = choices.get(index);
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a valid choice");
            }

        }
        return selected;
    }

    private void printProductList() {
        System.out.println("Products:");
        for (Product p : products.values()) {
            System.out.println(p.toString());
        }
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
        Shipping shipping;
        System.out.println("Choose a delivery method:");
        for ( Shipping.DeliveryOptions deliveryOption : Shipping.DeliveryOptions.values()){
            System.out.printf("%s%n", deliveryOption.name());
        }
        while (true){
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals(Shipping.DeliveryOptions.HomeDelivery.name().toLowerCase())){
                shipping = new Shipping(Shipping.DeliveryOptions.HomeDelivery);
                break;
            } else if (input.equals(Shipping.DeliveryOptions.StandardDelivery.name().toLowerCase())) {
                shipping = new Shipping(Shipping.DeliveryOptions.StandardDelivery);
                break;
            }
            System.out.println("Please enter a valid shipping method.");
        }

        Payment payment;
        System.out.println("Choose a payment method:");
        for ( Payment.PaymentMethod paymentMethod : Payment.PaymentMethod.values()){
            System.out.printf("%s%n", paymentMethod.name());
        }
        while (true){
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals(Payment.PaymentMethod.Cash.name().toLowerCase())){
                payment = new Payment(Payment.PaymentMethod.Cash, activeShoppingCart.getTotalCost());
                break;
            } else if (input.equals(Payment.PaymentMethod.CreditCard.name().toLowerCase())) {
                payment = new Payment(Payment.PaymentMethod.CreditCard, activeShoppingCart.getTotalCost());
                break;
            }
            System.out.println("Please enter a valid payment method.");
        }
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
        menu.put("List products", () -> printProductList());
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
        initializeProducts();
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

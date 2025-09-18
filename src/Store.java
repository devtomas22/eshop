import java.util.*;
import java.util.concurrent.Callable;

public class Store {
    static Store singleObject;
    ShoppingCart activeShoppingCart = new ShoppingCart();
    ArrayList<Order> activeOrders = new ArrayList<Order>();
    ArrayList<Order> finishedOrders =new ArrayList<Order>();
    // Map<String, Product> products = new HashMap<>();

    private Store() {}
    // HashMap<Product, Integer> stock = new HashMap<Product, Integer>();

    private void simulateShopper() {
        CustomerManager.getInstance().registerCustomer(new Customer("Sven", "Karlsson", "Näktergalsvägen 40", "12345", "sven@oracle.se"));
        CustomerManager.getInstance().registerCustomer(new Customer("Karl", "Gustavsson", "Main Street 0", "555-123", "karl@lexicon-it.se"));

        this.activeShoppingCart.addToCart("ComfyCloud Memory Foam Pillow", 5);
    }

    private void printCart() {
        System.out.println("Cart contents: ");
        activeShoppingCart.showCart();
    }

    private void addItemToCart(Scanner scanner) {
        Map<String, Callable<Product>> menu = new HashMap<>();
        for (Product p : Inventory.getInstance().getProductsInInventory().values()) {
            menu.put(p.compactString(), () -> p);
        }
        System.out.println("Select a product");
        Product p = MenuRunner.runMenuType(scanner, menu);
        activeShoppingCart.addToCart(p.getProductName(), 1);
    }

    private void checkoutCart(Scanner scanner) {
        Customer customer = null;
        if (CustomerManager.getInstance().getActiveCustomer() != null) {
            System.out.println("Please verify whether this information matches your account: yes/no");
            System.out.println(CustomerManager.getInstance().getActiveCustomer().censoredAccountDetails());
            String choice = scanner.nextLine().trim();
            if (choice.equals("yes")) {
                customer = CustomerManager.getInstance().getActiveCustomer();
            }
        }
        if (customer == null) {
            CustomerManager.getInstance().readInputAndAddCustomer(scanner);
            customer = CustomerManager.getInstance().getActiveCustomer();
        }

        System.out.println("Choose a delivery method:");

        Map<String, Callable<Shipping>> menu = new HashMap<>();
        menu.put("Home Delivery", () -> { return new Shipping(Shipping.DeliveryOptions.HomeDelivery); });
        menu.put("Standard Delivery", () -> { return new Shipping(Shipping.DeliveryOptions.StandardDelivery); });
        Shipping shipping = MenuRunner.runMenuType(scanner, menu);

        System.out.println("Choose a payment method:");

        Map<String, Callable<Payment>> paymentMenu = new HashMap<>();
        paymentMenu.put("Cash", () -> new Payment(Payment.PaymentMethod.CreditCard, activeShoppingCart.getTotalCost()));
        paymentMenu.put("Credit Card", () -> { return new Payment(Payment.PaymentMethod.CreditCard, activeShoppingCart.getTotalCost()); });
        Payment payment = MenuRunner.runMenuType(scanner, paymentMenu);

        Order order = new Order(customer, shipping, payment, activeShoppingCart.getProducts());

        this.activeShoppingCart = new ShoppingCart();
        this.activeOrders.add(order);

        System.out.println("Order created!");
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Runnable> menu = new LinkedHashMap<>();
        menu.put("List products", () -> Inventory.getInstance().printProductList());
        menu.put("Show cart", () -> printCart());
        menu.put("Add item to cart", () -> addItemToCart(scanner));
        menu.put("Checkout cart", () -> checkoutCart(scanner));
        menu.put("Manage customers", () -> {
            Map<String, Runnable> submenu = new HashMap<>();
            submenu.put("Add customer", () -> CustomerManager.getInstance().readInputAndAddCustomer(scanner));
            submenu.put("Remove customer", () -> CustomerManager.getInstance().readInputAndRemoveCustomer(scanner));
            submenu.put("List customers", () -> CustomerManager.getInstance().printCustomers());
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

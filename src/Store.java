import java.util.*;
import java.util.concurrent.Callable;

public class Store {
    static Store singleObject;
    ShoppingCart activeShoppingCart = new ShoppingCart();
    ArrayList<Order> activeOrders = new ArrayList<Order>();
    ArrayList<Order> finishedOrders =new ArrayList<Order>();
    Map<String, Product> products = new HashMap<>();
    // HashMap<Product, Integer> stock = new HashMap<Product, Integer>();
    public static Store getInstance(){
        if(singleObject == null){
            singleObject = new Store();
        }
        return singleObject;
    }

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
        this.activeShoppingCart.addToCart("ComfyCloud Memory Foam Pillow", 5);
        // convertShoppingCartToOrder();
        runMenu();
    }

    public String GetChoice(Scanner scanner, List<String> list) {
        HashMap<Integer, String> choices = new  HashMap<>();
        for (int i= 0; i < list.size(); i++) {
            System.out.printf("%d: %s %n", i, list.get(i));
            choices.put(i, list.get(i));
        }
        System.out.println("Enter your choices");
        int index = scanner.nextInt();
        return choices.get(index);
    }

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean quitMainLoop = false;
        while (!quitMainLoop) {
            List<String> choices = new ArrayList<>(Arrays.asList(
                    "List products",
                    "Add product",
                    "Show cart",
                    "Go to checkout",
                    "Stop shopping"
            ));
            switch(GetChoice(scanner, choices)) {
                case "List products" -> {
                    System.out.println("Products:");
                    for (Product p : products.values()) {
                        System.out.println(p.toString());
                    }
                }
                case "Add product" -> {

                }
                case "Show cart" -> {
                    System.out.println("Cart contents: ");
                    activeShoppingCart.showCart();
                }
                case "Go to checkout" -> {

                }
                case "Stop shopping" -> {
                    quitMainLoop = true;
                }
            }
        }
        scanner.close();
    }

    public void startShopping (){
        initializeProducts();
        simulateShopper();

    }

}

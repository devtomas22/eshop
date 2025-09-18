import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Product {
    private String productName;
    private double price;
    private String productDescription;
    private int numberOfItemsInStock;

    public Product(String productName, double price, String productDescription, int numberOfItemsInStock) {
        this.productName = productName;
        this.price = price;
        this.productDescription = productDescription;
        this.numberOfItemsInStock = numberOfItemsInStock;
    }

    public String compactString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name = ");
        sb.append(this.productName);
        sb.append("\n");
        sb.append("price = ");
        sb.append(this.price);
        sb.append("€\n");
        sb.append("productDescription = ");
        sb.append(this.productDescription);
        sb.append("\n");
        sb.append("number in stock = ");
        sb.append(this.numberOfItemsInStock);
        sb.append("\n");
        return sb.toString();
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getNumberOfItemsInStock() {
        return numberOfItemsInStock;
    }

    public void setNumberOfItemsInStock(int numberOfItemsInStock) {
        this.numberOfItemsInStock = numberOfItemsInStock;
    }
}

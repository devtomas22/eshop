import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Product {
    String productName;
    double price;
    String productDescription;
    int numberOfItemsInStock;
    Product(String productName, double price, String productDescription, int numberOfItemsInStock) {
        this.productName = productName;
        this.price = price;
        this.productDescription = productDescription;
        this.numberOfItemsInStock = numberOfItemsInStock;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("name = ");
        sb.append(this.productName);
        sb.append("\n");
        sb.append("price = ");
        sb.append(this.price);
        sb.append("\n");
        sb.append("productDescription = ");
        sb.append(this.productDescription);
        sb.append("\n");
        return sb.toString();
    }
}

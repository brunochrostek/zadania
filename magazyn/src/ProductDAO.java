import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalValue() {
        double sum = 0;
        for (Product p : products) {
            sum = sum + (p.getPrice() * p.getQuantity());
        }
        return sum;
    }
}
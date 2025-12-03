package POS;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void addProduct(Product p) {
        items.add(new CartItem(p, 1));
    }

    public void removeProduct(Product p) {
    }

    public double getSubtotal() {
        double sum = 0.0;
        for (CartItem ci : items) {
            sum += ci.getTotalPrice();
        }
        return sum;
    }
}

package POS;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;

    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return 0;
    }

    public void incrementQuantity() {
    }

    public double getTotalPrice() {
        return 0.0;
    }

}

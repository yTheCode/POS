package POS;

public abstract class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    // GetName
    public String getName() {
        return name;
    }
    // GetPrice
    public double getPrice() {
        return price;
    }
    //ToString
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }
}

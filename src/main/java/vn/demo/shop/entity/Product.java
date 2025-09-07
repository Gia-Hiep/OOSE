package vn.demo.shop.entity;

public class Product {
    private long id;
    private String name;
    private String description;
    private Category category;
    private double price;
    private int stock;
    private double discountPercent;

    public Product() {}
    public Product(long id, String name, String desc, Category category, double price, int stock) {
        this.id = id; this.name = name; this.description = desc; this.category = category;
        this.price = price; this.stock = stock; this.discountPercent = 0.0;
    }

    public double getEffectivePrice() { return price * (1 - discountPercent / 100.0); }

    // Getters/Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public double getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(double discountPercent) { this.discountPercent = discountPercent; }
}

package vn.demo.shop.entity;

public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem() {}
    public OrderItem(Product p, int q){ this.product = p; this.quantity = q; }

    public double getLineTotal(){ return product.getEffectivePrice() * quantity; }

    public Product getProduct(){ return product; }
    public void setProduct(Product product){ this.product = product; }
    public int getQuantity(){ return quantity; }
    public void setQuantity(int quantity){ this.quantity = quantity; }
}

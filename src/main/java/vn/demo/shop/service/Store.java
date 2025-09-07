package vn.demo.shop.service;

import org.springframework.stereotype.Component;
import vn.demo.shop.entity.*;

import java.util.*;

@Component
public class Store {
    private final List<Product> products = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private long productSeq = 1000;
    private long orderSeq = 1;

    public Store(){ seed(); }

    private long nextProductId(){ return ++productSeq; }
    private long nextOrderId(){ return orderSeq++; }

    private void seed(){
        products.add(new Product(nextProductId(), "Laptop X", "14\" i5/16GB", Category.ELECTRONICS, 1500.0, 8));
        products.add(new Product(nextProductId(), "Tai nghe", "Bluetooth 5.3", Category.ELECTRONICS, 60.0, 25));
        products.add(new Product(nextProductId(), "Gạo ST25", "Bao 5kg", Category.GROCERY, 8.9, 100));
        products.add(new Product(nextProductId(), "Sách Java OOP", "Cơ bản đến nâng cao", Category.BOOKS, 12.5, 40));
        products.add(new Product(nextProductId(), "Figure Nakroth", "1/7 scale", Category.TOYS, 199.0, 5));
    }

    public List<Product> allProducts(){ return new ArrayList<>(products); }

    public Product findProduct(long id){
        for (Product p : products) if (p.getId() == id) return p;
        return null;
    }

    public void updateProduct(long id, String name, String desc, Category category, Double price, Integer stock){
        Product p = findProduct(id);
        if (p == null) throw new IllegalArgumentException("Không tìm thấy sản phẩm");

        if (name != null && !name.isBlank()) p.setName(name);
        if (desc != null) p.setDescription(desc);
        if (category != null) p.setCategory(category);
        if (price != null && price >= 0) p.setPrice(price);
        if (stock != null && stock >= 0) p.setStock(stock);
    }

    public List<Product> sortByPrice(boolean asc){
        List<Product> copy = allProducts();
        Comparator<Product> cmp = Comparator.comparingDouble(Product::getEffectivePrice);
        if (!asc) cmp = cmp.reversed();
        copy.sort(cmp); return copy;
    }

    public List<Product> filterByCategory(Category c){
        List<Product> out = new ArrayList<>();
        for (Product p : products) if (p.getCategory() == c) out.add(p);
        return out;
    }

    public Map<Category, Double> inventoryValueByCategory(){
        Map<Category, Double> totals = new HashMap<>();
        for (Category c : Category.values()) totals.put(c, 0.0);
        for (Product p : products) {
            double v = p.getEffectivePrice() * p.getStock();
            totals.put(p.getCategory(), totals.get(p.getCategory()) + v);
        }
        return totals;
    }

    public void setDiscount(long id, double percent){
        if (percent < 0 || percent > 100) throw new IllegalArgumentException("% giảm 0..100");
        Product p = findProduct(id);
        if (p == null) throw new IllegalArgumentException("Không tìm thấy sản phẩm");
        p.setDiscountPercent(percent);
    }

    public Order createOrder(Map<Long, Integer> qtyById){
        for (Map.Entry<Long, Integer> e : qtyById.entrySet()) {
            long pid = e.getKey(); int qty = e.getValue();
            Product p = findProduct(pid);
            if (p == null) throw new IllegalArgumentException("Không tìm thấy sản phẩm #" + pid);
            if (qty <= 0) throw new IllegalArgumentException("Số lượng phải > 0 cho #" + pid);
            if (qty > p.getStock()) throw new IllegalArgumentException("Không đủ tồn cho #" + pid);
        }
        Order order = new Order(nextOrderId());
        for (Map.Entry<Long, Integer> e : qtyById.entrySet()) {
            Product p = findProduct(e.getKey()); int qty = e.getValue();
            p.setStock(p.getStock() - qty);
            order.addItem(new OrderItem(p, qty));
        }
        orders.add(order);
        return order;
    }

    public List<Order> allOrders(){ return new ArrayList<>(orders); }
}

package vn.demo.shop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private long id;
    private LocalDateTime createdAt = LocalDateTime.now();
    private final List<OrderItem> items = new ArrayList<>();

    public Order() {}
    public Order(long id){ this.id = id; }

    public void addItem(OrderItem it){ items.add(it); }
    public List<OrderItem> getItems(){ return Collections.unmodifiableList(items); }

    public double getTotal(){
        double sum = 0; for (OrderItem it : items) sum += it.getLineTotal(); return sum;
    }

    public long getId(){ return id; }
    public void setId(long id){ this.id = id; }
    public LocalDateTime getCreatedAt(){ return createdAt; }
}

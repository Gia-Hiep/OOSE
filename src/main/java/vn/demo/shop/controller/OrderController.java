package vn.demo.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.demo.shop.entity.Order;
import vn.demo.shop.entity.Product;
import vn.demo.shop.service.Store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    private final Store store;
    public OrderController(Store store){ this.store = store; }

    @GetMapping("/order")
    public String orderForm(Model model){
        List<Product> products = store.allProducts();
        model.addAttribute("products", products);
        return "order";
    }

    @PostMapping("/order")
    public String placeOrder(@RequestParam Map<String, String> params, Model model){
        Map<Long, Integer> qtyById = new HashMap<>();
        for (String key : params.keySet()) {
            if (key.startsWith("qty_")) {
                String idStr = key.substring(4);
                try {
                    long pid = Long.parseLong(idStr);
                    int qty = Integer.parseInt(params.get(key));
                    if (qty > 0) qtyById.put(pid, qty);
                } catch (NumberFormatException ignored) {}
            }
        }
        if (qtyById.isEmpty()) {
            model.addAttribute("error", "Bạn chưa nhập số lượng cho sản phẩm nào.");
            model.addAttribute("products", store.allProducts());
            return "order";
        }
        Order order = store.createOrder(qtyById);
        model.addAttribute("order", order);
        model.addAttribute("orders", store.allOrders());
        return "order-success";
    }
}

package vn.demo.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import vn.demo.shop.entity.Category;
import vn.demo.shop.service.Store;

import java.util.Map;

@Controller
public class InventoryController {
    private final Store store;
    public InventoryController(Store store){ this.store = store; }

    @GetMapping("/inventory")
    public String inventory(Model model){
        Map<Category, Double> totals = store.inventoryValueByCategory();
        model.addAttribute("totals", totals);
        model.addAttribute("categories", Category.values());
        return "inventory";
    }
}

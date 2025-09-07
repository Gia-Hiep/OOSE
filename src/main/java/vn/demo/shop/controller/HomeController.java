package vn.demo.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.demo.shop.entity.Category;
import vn.demo.shop.entity.Product;
import vn.demo.shop.service.Store;

import java.util.List;

@Controller
public class HomeController {
    private final Store store;
    public HomeController(Store store){ this.store = store; }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String sort,
                       @RequestParam(required = false) Category category,
                       Model model){
        List<Product> products;
        if (category != null) {
            products = store.filterByCategory(category);
        } else if ("priceAsc".equalsIgnoreCase(sort)) {
            products = store.sortByPrice(true);
        } else if ("priceDesc".equalsIgnoreCase(sort)) {
            products = store.sortByPrice(false);
        } else {
            products = store.allProducts();
        }
        model.addAttribute("products", products);
        model.addAttribute("categories", Category.values());
        model.addAttribute("selectedCategory", category);
        model.addAttribute("sort", sort);
        return "home";
    }
}

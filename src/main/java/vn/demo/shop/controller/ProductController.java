package vn.demo.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.demo.shop.entity.Category;
import vn.demo.shop.entity.Product;
import vn.demo.shop.service.Store;

/** Controller xử lý cập nhật và giảm giá sản phẩm. */
@Controller @RequestMapping("/products")
public class ProductController {
    private final Store store;
    public ProductController(Store store){ this.store = store; }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable long id, Model model){
        Product p = store.findProduct(id);
        if (p == null) throw new IllegalArgumentException("Không tìm thấy sản phẩm");
        model.addAttribute("p", p);
        model.addAttribute("categories", Category.values());
        return "product-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable long id,
                         @RequestParam String name,
                         @RequestParam(required = false) String description,
                         @RequestParam Category category,
                         @RequestParam double price,
                         @RequestParam int stock){
        store.updateProduct(id, name, description, category, price, stock);
        return "redirect:/";
    }

    @GetMapping("/{id}/discount")
    public String discountForm(@PathVariable long id, Model model){
        Product p = store.findProduct(id);
        if (p == null) throw new IllegalArgumentException("Không tìm thấy sản phẩm");
        model.addAttribute("p", p);
        return "product-discount";
    }

    @PostMapping("/{id}/discount")
    public String applyDiscount(@PathVariable long id, @RequestParam double percent){
        store.setDiscount(id, percent);
        return "redirect:/";
    }
}

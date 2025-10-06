package com.demo.mvc.controller;
import com.demo.mvc.entity.Product;
import com.demo.mvc.entity.ProductCategory;
import com.demo.mvc.service.ProductService;
import com.demo.mvc.service.ProductCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
@Controller @RequestMapping("/products")
public class ProductController {
  private final ProductService productService;
  private final ProductCategoryService categoryService;
  public ProductController(ProductService productService, ProductCategoryService categoryService){
    this.productService = productService; this.categoryService = categoryService;
  }
  @GetMapping
  public String list(Model model){
      model.addAttribute("products", productService.findAll());
      return "products";
  }

  @GetMapping("/new")
  public String form(Model model){
      model.addAttribute("product", new Product());
      model.addAttribute("categories", categoryService.findAll());
      return "product_form";
  }

  @PostMapping("/save")
  public String save(@RequestParam BigDecimal Price, @RequestParam BigDecimal Weight, @RequestParam Integer ProductCategoryID){
    Product p = new Product();
    p.setPrice(Price);
    p.setWeight(Weight);
    ProductCategory c = new ProductCategory();
    c.setProduct_category_id(ProductCategoryID);
    p.setCategory(c);
    productService.save(p);
    return "redirect:/products";
  }
}

package com.demo.mvc.controller;
import com.demo.mvc.entity.ProductCategory;
import com.demo.mvc.service.ProductCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/categories")
public class CategoryController {

  private final ProductCategoryService service;
  public CategoryController(ProductCategoryService service){
      this.service = service;
  }

  @GetMapping
  public String list(Model model){
      model.addAttribute("categories", service.findAll());
      return "categories";
  }

  @GetMapping("/new")
  public String form(Model model){
      model.addAttribute("category", new ProductCategory());
      return "category_form";
  }

  @PostMapping("/save")
  public String save(@RequestParam(defaultValue="false") boolean CanBeShipped){
    ProductCategory c = new ProductCategory();
    c.setCan_be_shipped(CanBeShipped);
    service.save(c);
    return "redirect:/categories";
  }
}

package com.demo.mvc.controller;

import com.demo.mvc.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class I18nProductController {

    private final ProductRepository productRepository;

    public I18nProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/i18n-products")
    public String i18nProducts(@RequestParam(defaultValue = "VI") String lang, Model model) {
        List<Object[]> rows = productRepository.findProductsByLanguage(lang);

        model.addAttribute("lang", lang);
        model.addAttribute("rows", rows);

        return "i18n_products";
    }
}

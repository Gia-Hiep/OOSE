package com.demo.mvc.repository;
import com.demo.mvc.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> { }

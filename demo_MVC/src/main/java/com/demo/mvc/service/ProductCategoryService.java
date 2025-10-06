package com.demo.mvc.service;
import com.demo.mvc.entity.ProductCategory;
import com.demo.mvc.repository.ProductCategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductCategoryService {
  private final ProductCategoryRepository repo;

  public ProductCategoryService(ProductCategoryRepository repo){
      this.repo = repo;
  }

  public List<ProductCategory> findAll(){
      return repo.findAll();
  }

  public ProductCategory save(ProductCategory c){
      return repo.save(c);
  }
}

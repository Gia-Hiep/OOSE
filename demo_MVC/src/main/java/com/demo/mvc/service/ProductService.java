package com.demo.mvc.service;
import com.demo.mvc.entity.Product; import com.demo.mvc.repository.ProductRepository;
import org.springframework.stereotype.Service; import java.util.List;
@Service
public class ProductService {
  private final ProductRepository repo;
  public ProductService(ProductRepository repo){ this.repo = repo; }
  public List<Product> findAll(){ return repo.findAll(); }
  public Product save(Product p){ return repo.save(p); }
}

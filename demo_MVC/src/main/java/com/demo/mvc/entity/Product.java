package com.demo.mvc.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
@Entity
@Table(name="products")
@Data
public class Product {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer product_id;
  @Column(nullable=false)
  private BigDecimal price;
  @Column(nullable=false)
  private BigDecimal weight;
  @ManyToOne @JoinColumn(name="product_category_id")
  private ProductCategory category;
}

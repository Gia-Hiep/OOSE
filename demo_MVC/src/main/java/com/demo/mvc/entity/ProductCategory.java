package com.demo.mvc.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name="product_category")
@Data
public class ProductCategory {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer product_category_id;
  @Column(nullable=false)
  private boolean can_be_shipped;

}

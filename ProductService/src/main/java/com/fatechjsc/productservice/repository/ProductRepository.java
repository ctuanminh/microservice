package com.fatechjsc.productservice.repository;

import com.fatechjsc.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

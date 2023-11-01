package com.fatechjsc.productservice.repository;

import com.fatechjsc.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}

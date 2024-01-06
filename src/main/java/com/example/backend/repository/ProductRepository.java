package com.example.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

package com.example.demo.repository;

import com.example.demo.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoR extends JpaRepository<Producto,Integer> {
}

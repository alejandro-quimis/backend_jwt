package com.example.demo.controller;

import com.example.demo.models.Producto;
import com.example.demo.repository.ProductoR;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/producto")
@CrossOrigin(origins = "*")
public class ProductoC extends ControladorGenerico<Producto> {

    public ProductoC(ProductoR dao) {
        super(dao);
    }

}
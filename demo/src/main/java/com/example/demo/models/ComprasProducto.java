package com.example.demo.models;

public class ComprasProducto extends Compras{
    private Producto productos;

    public ComprasProducto (){
        super();
    }

    public Producto getProductos() {
        return productos;
    }

    public void setProductos(Producto productos) {
        this.productos = productos;
    }
}

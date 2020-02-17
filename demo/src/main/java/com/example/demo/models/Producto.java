package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="producto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    private int codigo;
    private String nombre;
    private double valor;

    @Id
    @Column(name = "codigo", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 100)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "valor", nullable = false, precision = 0)
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}

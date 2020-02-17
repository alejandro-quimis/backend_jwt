package com.example.demo.models;

import com.example.demo.models.seguridad.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="compras")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SQLDelete(sql="UPDATE compras SET estado = 'i' WHERE codigo = ?")
@Where(clause="estado ='a'")
public class Compras {
    private int codigo;
    @Column(name = "producto")
    private Integer producto;
    @Column(name = "usuario")
    private Integer usuario;
    private int cantidad;
    private Date fecha;
    private double total;
    private String estado;

    @Basic
    @Column(name = "estado", nullable = false, length = 1)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    @ManyToOne
    @JoinColumn(name = "producto", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Producto productoByProducto;
    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Usuario usuarioByUsuario;

    @Id
    @Column(name = "codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    @Basic
    @Column(name = "cantidad", nullable = false)
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "total", nullable = false, precision = 0)
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
/*
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "producto", referencedColumnName = "codigo", insertable = false)
    public Producto getProductoByProducto() {
        return productoByProducto;
    }

    public void setProductoByProducto(Producto productoByProducto) {
        this.productoByProducto = productoByProducto;
    }

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "codigo", insertable = false )
    public Usuario getUsuarioByUsuario() {
        return usuarioByUsuario;
    }

    public void setUsuarioByUsuario(Usuario usuarioByUsuario) {
        this.usuarioByUsuario = usuarioByUsuario;
    }
*/}

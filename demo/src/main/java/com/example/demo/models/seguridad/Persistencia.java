package com.example.demo.models.seguridad;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "persistencia")
@SQLDelete(sql="UPDATE persistencia SET estado = 'i' WHERE nombreusuario = ?")
@Where(clause="estado ='a'")
public class Persistencia {
    @Id
    @NotBlank
    @Column(unique = true, name="nombreusuario")
    private String nombreusuario;

    @NotNull
    @Column(name="token")
    private String token;

    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private Character estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Persistencia() {
    }

    public Persistencia(String nombreusuario, String token) {
        this.nombreusuario=nombreusuario;
        this.token=token;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

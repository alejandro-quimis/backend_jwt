package com.example.demo.repository;

import com.example.demo.models.seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioR extends JpaRepository<Usuario,Integer> {
    @Override
    @Query("select t from #{#entityName} t where t.estado='a'")
    List<Usuario> findAll();

    Optional<Usuario> findByNombreUsuario(String nu);
    boolean existsByNombreUsuario(String nu);
    boolean existsByEmail(String email);

/*
    public Optional<Usuario> getByNombreUsuario(String nu){
        return usuarioRepository.findByNombreUsuario(nu);
    }

    public boolean existePorNombre(String nu){
        return usuarioRepository.existsByNombreUsuario(nu);
    }

    public  boolean existePorEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void guardar(Usuario usuario){
        usuarioRepository.save(usuario);
    }*/

}

package com.example.demo.repository;

import com.example.demo.emuns.RolNombre;
import com.example.demo.models.seguridad.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}

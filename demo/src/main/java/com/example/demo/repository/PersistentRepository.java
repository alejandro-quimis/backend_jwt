package com.example.demo.repository;

import com.example.demo.models.seguridad.Persistencia;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersistentRepository extends CrudRepository<Persistencia,Long> {
    //Optional<Persistencia> findByToken(String token);
    Persistencia findByToken(String token);
    boolean existsByToken(String token);
}

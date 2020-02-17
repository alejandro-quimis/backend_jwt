package com.example.demo.repository;

import com.example.demo.models.Compras;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompraR  extends JpaRepository<Compras,Integer> {


    @Query(value = "select * from dbpasantia.compras where compras.usuario = :id", nativeQuery = true)
    List<Compras> findByUsuario(@Param("id") int id);



    @Override
    @Query("select t from #{#entityName} t where t.estado='a'")
    List<Compras> findAll();

    @Query(value = "select * from dbpasantia.compras where dbpasantia.compras.estado='a' and dbpasantia.compras.usuario= :id ", nativeQuery = true)
    List<Compras> findByUsuario(@Param("id") Integer id);





}

package com.example.demo.controller;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorGenerico<T> {

    protected final JpaRepository<T, Integer> dao ;

    public ControladorGenerico(JpaRepository<T,Integer> dao){
        this.dao=dao;
    }


    @GetMapping("/listar")
    public ResponseEntity<List<T>> getLista(){
        List<T> lista = dao.findAll();
        if(!lista.isEmpty())
            return new ResponseEntity<List<T>>(lista, HttpStatus.OK);
        else
            return new ResponseEntity("NO SE ENCUENTRA LA INFORMACION",HttpStatus.NOT_FOUND);
    }


    @GetMapping("/detalle/{id}")
    public ResponseEntity<T> getOne(@PathVariable Integer id){
        if(!dao.existsById(id))
            return new ResponseEntity("NO EXISTE ", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<T>((T) dao.findById(id), HttpStatus.OK);
    }

    @PostMapping("nuevo")
    public ResponseEntity<?> create(@RequestBody T entity){
        try {
            dao.save(entity);
            return new ResponseEntity("GUARDADO", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.toString(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update( @RequestBody T entity,@PathVariable("id") Integer id) {
        if(!dao.existsById(id))
            return new ResponseEntity("ESTE ITEM NO EXISTE", HttpStatus.NOT_FOUND);
        else{
            dao.save(entity);
            return new ResponseEntity("ITEM ACTUALIZADO", HttpStatus.CREATED);
        }

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Integer id){
        if(!dao.existsById(id))
            return new ResponseEntity("NO EXISTE ESTE ITEM", HttpStatus.NOT_FOUND);
        else {
            dao.deleteById(id);
            return new ResponseEntity("ITEM ELIMINADO", HttpStatus.OK);
        }
    }


}
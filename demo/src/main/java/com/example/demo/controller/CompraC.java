package com.example.demo.controller;

import com.example.demo.models.Compras;
import com.example.demo.models.ComprasProducto;
import com.example.demo.models.Producto;
import com.example.demo.repository.CompraR;
import com.example.demo.repository.ProductoR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("api/compras")
public class CompraC <COmpras> {
    @Autowired
    private CompraR dao;

    @Autowired
    private ProductoR pro;

    private ComprasProducto  CP = new ComprasProducto();

    public CompraC() {
    }


    @GetMapping("/listar")
    public ResponseEntity<List<Compras>> getLista(){
        List<Compras> lista = dao.findAll();
        if(!lista.isEmpty())
            return new ResponseEntity<List<Compras>>(lista, HttpStatus.OK);
        else
            return new ResponseEntity("NO SE ENCUENTRA LA INFORMACION",HttpStatus.NOT_FOUND);
    }


    @GetMapping("/detalle/{id}")
    public ResponseEntity<Compras> getOne(@PathVariable Integer id){
        if(!dao.existsById(id))
            return new ResponseEntity("NO EXISTE ", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity( dao.findById(id), HttpStatus.OK);
    }

    @PostMapping("nuevo")
    public ResponseEntity<?> create(@RequestBody Compras entity){
        try {
            dao.save(entity);
            return new ResponseEntity("GUARDADO", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.toString(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update( @RequestBody Compras entity,@PathVariable("id") Integer id) {
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




    @GetMapping("/obtenercomprasproductos/{id}")
    public ResponseEntity<ComprasProducto> getcomprasproductos(@PathVariable("id") Integer id){
    try {
        Optional<Compras> c = dao.findById(id);
        Optional<Producto> p = pro.findById(c.get().getProducto());
        CP.setCantidad(c.get().getCantidad());
        CP.setCodigo(c.get().getCodigo());
        CP.setFecha(c.get().getFecha());
        CP.setTotal(c.get().getTotal());
        CP.setUsuario(c.get().getUsuario());
        CP.setProducto(c.get().getProducto());
        CP.setProductos(p.get());

        System.out.println(c.isPresent());
        System.out.println(p.isPresent());
        if(CP!=null)
            return new ResponseEntity(CP, HttpStatus.OK);
    }catch (Exception e){
            return new ResponseEntity("NO SE ENCUENTRA LA INFORMACION", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity("NO SE ENCUENTRA LA INFORMACION", HttpStatus.NOT_FOUND);

    }
    @GetMapping("/obtenercompras/{id}")
    public ResponseEntity<List<Compras>> getcompras(@PathVariable("id") Integer id){
        List<Compras> lista = dao.findByUsuario(id);
        if(!lista.isEmpty())
            return new ResponseEntity<List<Compras>>(lista, HttpStatus.OK);
        else
            return new ResponseEntity("NO SE ENCUENTRA LA INFORMACION",HttpStatus.NOT_FOUND);
    }

//busca la compra por id de usuario
    @GetMapping("/obtenercomprasproductosusuario/{id}")
    public ResponseEntity<List<ComprasProducto>> getcomprasproductosusario(@PathVariable("id") Integer id){
        try {
            List<Compras> c = dao.findByUsuario(id);
            Optional<Producto> p ;
            List<ComprasProducto>  CP = new ArrayList<ComprasProducto>();
            ComprasProducto  compras = new ComprasProducto();
            for (int i = 0; i <c.size() ; i++) {
                p = pro.findById(c.get(i).getProducto());
                compras.setCantidad(c.get(i).getCantidad());
                compras.setCodigo(c.get(i).getCodigo());
                compras.setFecha(c.get(i).getFecha());
                compras.setTotal(c.get(i).getTotal());
                compras.setUsuario(c.get(i).getUsuario());
                compras.setProducto(c.get(i).getProducto());
                compras.setProductos(p.get());
                compras.setEstado(c.get(i).getEstado());
                CP.add(compras);
                compras = new ComprasProducto();
            }


            if(!CP.isEmpty())
                return new ResponseEntity<List<ComprasProducto>>(CP, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("NO SE ENCUENTRA LA INFORMACION", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("NO SE ENCUENTRA LA INFORMACION", HttpStatus.NOT_FOUND);

    }

}
package com.example.demo.controller;

import com.example.demo.models.seguridad.Usuario;
import com.example.demo.repository.UsuarioR;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8100")
@RequestMapping("api/usuario")
public class UsuarioC extends ControladorGenerico<Usuario> {

    public UsuarioC(UsuarioR dao) {
        super(dao);
    }



}

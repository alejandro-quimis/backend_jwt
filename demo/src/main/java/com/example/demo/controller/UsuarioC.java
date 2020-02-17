package com.example.demo.controller;

import com.example.demo.models.seguridad.Usuario;
import com.example.demo.repository.UsuarioR;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioC extends ControladorGenerico<Usuario> {

    public UsuarioC(UsuarioR dao) {
        super(dao);
    }



}

package com.example.demo.controller;

import com.example.demo.DTO.JwtDTO;
import com.example.demo.DTO.LoginUsuario;
import com.example.demo.DTO.NuevoUsuario;
import com.example.demo.emuns.RolNombre;
import com.example.demo.models.seguridad.Persistencia;
import com.example.demo.models.seguridad.Rol;
import com.example.demo.models.seguridad.Usuario;
import com.example.demo.repository.PersistentRepository;
import com.example.demo.repository.RolRepository;
import com.example.demo.repository.UsuarioR;
import com.example.demo.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    static String jwt;
    @Autowired
    PersistentRepository persistent;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioR usuarioService;

    @Autowired
    RolRepository rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("campos vacíos o email inválido", HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity("ese nombre ya existe", HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity("ese email ya existe", HttpStatus.BAD_REQUEST);
        System.out.println("usuario");
        System.out.println(nuevoUsuario.getNombreUsuario());
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()),"a");
        System.out.println("usuario agregado");
        System.out.println(usuario.getNombreUsuario());
        Set<String> rolesStr = nuevoUsuario.getRoles();
        System.out.println(nuevoUsuario.getRoles().size());
        System.out.println(nuevoUsuario.getRoles());
        Set<Rol> roles = new HashSet<>();
        for (String rol : rolesStr) {
            switch (rol) {
                case "ADMIN":
                    System.out.println("hola mundo");
                    System.out.println(rolService.findById(1).get());
                    Rol rolAdmin = rolService.findByRolNombre(RolNombre.ROLE_ADMIN).get();
                    System.out.println(rolAdmin);
                    roles.add(rolAdmin);
                    break;
                default:
                    System.out.println("hola mundo user ");
                    System.out.println(rolService.findById(1).get());
                    Rol rolUser = rolService.findByRolNombre(RolNombre.ROLE_USER).get();
                    System.out.println(rolUser);
                    roles.add(rolUser);
            }
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity("usuario guardado", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity("campos vacíos o email inválido", HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
        );
        /*SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());*/
        /*System.out.println("----->"+authentication.toString());
        System.out.println(authentication.getAuthorities());
        System.out.println(authentication.getCredentials());
        System.out.println(authentication.getDetails());
        System.out.println(authentication.getPrincipal());
        System.out.println(authentication.isAuthenticated());*/
        SecurityContextHolder.getContext().setAuthentication(authentication);
        jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        System.out.println(jwtDTO.getNombreUsuario());
        //agregar a la bd

        Persistencia p = new Persistencia();
        p.setEstado('a');
        p.setFecha(new Date());
        p.setToken(jwtDTO.getToken());
        p.setNombreusuario(jwtDTO.getNombreUsuario());
        persistent.save(p);
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }
}

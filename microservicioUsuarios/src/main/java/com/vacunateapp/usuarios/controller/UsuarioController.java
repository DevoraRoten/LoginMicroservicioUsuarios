package com.vacunateapp.usuarios.controller;

import com.vacunateapp.usuarios.model.Usuario;
import com.vacunateapp.usuarios.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
//@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
@CrossOrigin(origins = "http://localhost:4200", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class UsuarioController {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/auth")
	public Principal user(Principal user) {
		return user;
	}
	
	
    private final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioRepository usuarioRepository;
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Metodo para buscar a todos los usuarios
    @GetMapping("/api/usuarios")
    public List<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    //Metodo para buscar usuarios por id
    @GetMapping("/api/usuarios/{id}")
    public ResponseEntity<Usuario> buscarUsuarioId(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Metodo para crear personal
    @PostMapping("/api/usuarios")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        if (usuario.getId() != null) {
            log.warn("Esta intentando crear un usuario con id");
            return ResponseEntity.badRequest().build();
        } else {
        	usuario.setPassword(encoder.encode(usuario.getPassword()));
            Usuario resultado = usuarioRepository.save(usuario);
            return ResponseEntity.ok(resultado);
        }
    }

    //Metodo para borrar personal
    @DeleteMapping("/api/usuarios/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
        } else {
            log.warn("Esta intentando borrar un usuario que no existe");
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/api/usuarios")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        if(usuario.getId() == null ){
            log.warn("Esta intentando modificar un usuario que no existe");
            return ResponseEntity.badRequest().build();
        }
        if(!usuarioRepository.existsById(usuario.getId())){
            log.warn("Esta intentando modificar un usuario que no existe");
            return ResponseEntity.notFound().build();
        }
        Usuario result = usuarioRepository.save(usuario);
        return ResponseEntity.ok(result);
    }
}
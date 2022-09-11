package com.vacunateapp.usuarios.repository;

import com.vacunateapp.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByRut(String rut);
    
    /*public void save(Usuario usuario) {
    	
    }*/
    
}

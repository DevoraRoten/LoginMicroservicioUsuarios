package com.vacunateapp.usuarios;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.vacunateapp.usuarios.model.Usuario;
import com.vacunateapp.usuarios.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class UsuariosApplicationTests {
	@Autowired
	private UsuarioRepository usuariorepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	  /*this.id = id;
      this.name = name;
      this.rut = rut;
      this.phone = phone;
      this.email = email;
      this.password = password;*/
    @Test
    void crearUser() {
		Usuario usuario = new Usuario();
		usuario.setName("Dan");
		usuario.setRut("1-4");
		usuario.setPhone("123456");
		usuario.setEmail("dan@gmail.com");
		usuario.setPassword(encoder.encode("123"));
		usuario.setAdmin(true);
		Usuario retorno = usuariorepository.save(usuario);
		
		assertTrue(retorno.getPassword().equalsIgnoreCase(usuario.getPassword()));
    }

}

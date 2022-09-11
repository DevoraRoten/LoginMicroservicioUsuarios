package com.vacunateapp.usuarios.services;

import com.vacunateapp.usuarios.model.Usuario;
import com.vacunateapp.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioImp implements UserDetailsService {


    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Usuario usuario = usuarioRepository.findByRut(username);
        

        List<GrantedAuthority> roles = new ArrayList<>();
        if(usuario.isAdmin()) {
        	roles.add(new SimpleGrantedAuthority("admin"));
        }else if(!usuario.isAdmin()) {
        	roles.add(new SimpleGrantedAuthority("personal"));
        }
        

        UserDetails details = new User(usuario.getName(), usuario.getPassword(), roles);
        return details;
    }

}

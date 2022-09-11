package com.vacunateapp.usuarios.model;

import javax.persistence.*;

@Entity
@Table(name = "app_usuario")
public class Usuario {

    //atritbutos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;
    @Column(name = "usr_nombre_completo", nullable = false)
    private String name;
    @Column(name = "usr_rut",nullable = false, unique = true)
    private String rut;
    @Column(name = "usr_telefono")
    private String phone;
    @Column(name = "usr_correo")
    private String email;
    @Column(name = "usr_contraseña")
    private String password;
    @Column(name = "usr_admin")
    private boolean admin;


    //constructor
    public Usuario() {
    }
    
    public Usuario(Long id, String name, String rut, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.rut = rut;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    
    

    //Getter & Setter
    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
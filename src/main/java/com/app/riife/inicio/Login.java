/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.inicio;

import java.util.Objects;


/**
 *
 * @author usuario
 */

public class Login {
  private String usuario;
  private String contraseña;
  private String contraseña2;

    public Login() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = Objects.nonNull(usuario) ? usuario.toUpperCase() : usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = Objects.nonNull(contraseña) ? contraseña.toUpperCase() : contraseña;
    }

    public String getContraseña2() {
        return contraseña2;
    }

    public void setContraseña2(String contraseña2) {
        this.contraseña2 = Objects.nonNull(contraseña2) ? contraseña2.toUpperCase() : contraseña2;
    }
  
  
}
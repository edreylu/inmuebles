/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.usuario;

/**
 *
 * @author usuario
 */
import com.app.inmuebles.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
  private int noUsuario;
    private String clave;
    private String pasaporte;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String telefono;
    private int idEstatus;
    private java.util.Date fechaAuditoria;
     private Roles rol = new Roles();
     private int noDepartamento;
     private String telefono2;
     private Integer noPersonalBm;
     private String nomUsuario;

    public Usuario(int noUsuario) {
        this.noUsuario = noUsuario;
    }
    
}
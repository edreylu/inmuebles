/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.usuario;

/**
 *
 * @author usuario
 */
import com.app.riife.roles.Roles;
import java.util.Date;


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

    public Usuario(int noUsuario, String clave, String pasaporte, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String telefono, int idEstatus, Date fechaAuditoria, int noDepartamento, String telefono2, Integer noPersonalBm, String nomUsuario) {
        this.noUsuario = noUsuario;
        this.clave = clave;
        this.pasaporte = pasaporte;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.telefono = telefono;
        this.idEstatus = idEstatus;
        this.fechaAuditoria = fechaAuditoria;
        this.noDepartamento = noDepartamento;
        this.telefono2 = telefono2;
        this.noPersonalBm = noPersonalBm;
        this.nomUsuario = nomUsuario;
    }

    public Usuario() {
    }

    public int getNoUsuario() {
        return noUsuario;
    }

    public void setNoUsuario(int noUsuario) {
        this.noUsuario = noUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Date getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(Date fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public int getNoDepartamento() {
        return noDepartamento;
    }

    public void setNoDepartamento(int noDepartamento) {
        this.noDepartamento = noDepartamento;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public Integer getNoPersonalBm() {
        return noPersonalBm;
    }

    public void setNoPersonalBm(Integer noPersonalBm) {
        this.noPersonalBm = noPersonalBm;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }
    
    
    
}
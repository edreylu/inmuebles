/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.roles;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Roles {
private String insertar;
    private String actualizar;
    private String eliminar;
    private String consultar;
    private String descargar;
    private Boolean insertarSel;
    private Boolean actualizarSel;
    private Boolean eliminarSel;
    private Boolean consultarSel;
    private Boolean descargarSel;
    private String clavebuscada;
   
    private Integer noRol;
    private String descripcion;
    private String nombreinsertar;

    public Roles(String insertar, String actualizar, String eliminar, String consultar, String descargar, Boolean insertarSel, Boolean actualizarSel, Boolean eliminarSel, Boolean consultarSel, Boolean descargarSel, String clavebuscada, Integer noRol, String descripcion, String nombreinsertar) {
        this.insertar = insertar;
        this.actualizar = actualizar;
        this.eliminar = eliminar;
        this.consultar = consultar;
        this.descargar = descargar;
        this.insertarSel = insertarSel;
        this.actualizarSel = actualizarSel;
        this.eliminarSel = eliminarSel;
        this.consultarSel = consultarSel;
        this.descargarSel = descargarSel;
        this.clavebuscada = clavebuscada;
        this.noRol = noRol;
        this.descripcion = descripcion;
        this.nombreinsertar = nombreinsertar;
    }

    public Roles() {
    }

    public String getInsertar() {
        return insertar;
    }

    public void setInsertar(String insertar) {
        this.insertar = insertar;
    }

    public String getActualizar() {
        return actualizar;
    }

    public void setActualizar(String actualizar) {
        this.actualizar = actualizar;
    }

    public String getEliminar() {
        return eliminar;
    }

    public void setEliminar(String eliminar) {
        this.eliminar = eliminar;
    }

    public String getConsultar() {
        return consultar;
    }

    public void setConsultar(String consultar) {
        this.consultar = consultar;
    }

    public String getDescargar() {
        return descargar;
    }

    public void setDescargar(String descargar) {
        this.descargar = descargar;
    }

    public Boolean getInsertarSel() {
        return insertarSel;
    }

    public void setInsertarSel(Boolean insertarSel) {
        this.insertarSel = insertarSel;
    }

    public Boolean getActualizarSel() {
        return actualizarSel;
    }

    public void setActualizarSel(Boolean actualizarSel) {
        this.actualizarSel = actualizarSel;
    }

    public Boolean getEliminarSel() {
        return eliminarSel;
    }

    public void setEliminarSel(Boolean eliminarSel) {
        this.eliminarSel = eliminarSel;
    }

    public Boolean getConsultarSel() {
        return consultarSel;
    }

    public void setConsultarSel(Boolean consultarSel) {
        this.consultarSel = consultarSel;
    }

    public Boolean getDescargarSel() {
        return descargarSel;
    }

    public void setDescargarSel(Boolean descargarSel) {
        this.descargarSel = descargarSel;
    }

    public String getClavebuscada() {
        return clavebuscada;
    }

    public void setClavebuscada(String clavebuscada) {
        this.clavebuscada = clavebuscada;
    }

    public Integer getNoRol() {
        return noRol;
    }

    public void setNoRol(Integer noRol) {
        this.noRol = noRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreinsertar() {
        return nombreinsertar;
    }

    public void setNombreinsertar(String nombreinsertar) {
        this.nombreinsertar = nombreinsertar;
    }
    
    
    
    public static Roles inicializarAdd(Roles rol) {
        rol.setInsertar("N");
        rol.setActualizar("N");
        rol.setEliminar("N");
        rol.setConsultar("N");
        rol.setDescargar("S");
        if (Objects.nonNull(rol.getInsertarSel())) {
            rol.setInsertar("S");
        }
        if (Objects.nonNull(rol.getActualizarSel())) {
            rol.setActualizar("S");
        }
        if (Objects.nonNull(rol.getEliminarSel())) {
            rol.setEliminar("S");
        }
        if (Objects.nonNull(rol.getConsultarSel())) {
            rol.setConsultar("S");
        }
        return rol;
    }

    public static Roles inicializarEdit(Roles rol) {
        rol.setInsertar("N");
        rol.setActualizar("N");
        rol.setEliminar("N");
        rol.setConsultar("N");
        rol.setDescargar("S");
        if (Objects.equals(rol.getInsertarSel(), true)) {
            rol.setInsertar("S");
        }
        if (Objects.equals(rol.getActualizarSel(), true)) {
            rol.setActualizar("S");
        }
        if (Objects.equals(rol.getEliminarSel(), true)) {
            rol.setEliminar("S");
        }
        if (Objects.equals(rol.getConsultarSel(), true)) {
            rol.setConsultar("S");
        }

        return rol;
    }
}

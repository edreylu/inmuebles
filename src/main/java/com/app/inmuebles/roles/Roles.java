/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.roles;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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

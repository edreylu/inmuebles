/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.roles;

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
}

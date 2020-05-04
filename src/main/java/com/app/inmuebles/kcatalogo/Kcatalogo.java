/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.kcatalogo;

import com.app.inmuebles.cuestionario.Cuestionario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Kcatalogo {
    private Cuestionario cuestionario;
    private String catalogo;
    private int claveCatalogo;
    private String descripCorta;
    private String descripcion;
    private String observaciones;
    private int idEstatus;
    private String llave;
}

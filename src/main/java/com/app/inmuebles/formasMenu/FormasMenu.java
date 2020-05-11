/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.formasMenu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author usuario
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FormasMenu {
    private int noFormaMenu;
    private String titulo;
    private String url;
    private String icono;
    private int noTipoPadre;
    private int noFormaPadre;
    private String nombrePapa;
    private String iconoPapa;
    private boolean menuSeleccionado;
    private String selec;
    private String clavebuscada;
    
    
}

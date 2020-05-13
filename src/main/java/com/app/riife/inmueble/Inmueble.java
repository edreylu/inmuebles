/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.inmueble;

import java.util.Date;
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
public class Inmueble {
 private int idInmueble;
 private String claveInmueble;
 private int idCentroTrabajo;
 private String jefaturaSector;
 private String zonaEscolar;
 private String direccionRegional;
 private String domicilio;
}

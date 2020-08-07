/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.inmueble;


/**
 *
 * @author Admin
 */
public class Inmueble {
 private int idInmueble;
 private String claveInmueble;
 private int idCentroTrabajo;
 private String jefaturaSector;
 private String zonaEscolar;
 private String direccionRegional;
 private String domicilio;

    public Inmueble(int idInmueble, String claveInmueble, int idCentroTrabajo, String jefaturaSector, String zonaEscolar, String direccionRegional, String domicilio) {
        this.idInmueble = idInmueble;
        this.claveInmueble = claveInmueble;
        this.idCentroTrabajo = idCentroTrabajo;
        this.jefaturaSector = jefaturaSector;
        this.zonaEscolar = zonaEscolar;
        this.direccionRegional = direccionRegional;
        this.domicilio = domicilio;
    }

    public Inmueble() {
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public String getClaveInmueble() {
        return claveInmueble;
    }

    public void setClaveInmueble(String claveInmueble) {
        this.claveInmueble = claveInmueble;
    }

    public int getIdCentroTrabajo() {
        return idCentroTrabajo;
    }

    public void setIdCentroTrabajo(int idCentroTrabajo) {
        this.idCentroTrabajo = idCentroTrabajo;
    }

    public String getJefaturaSector() {
        return jefaturaSector;
    }

    public void setJefaturaSector(String jefaturaSector) {
        this.jefaturaSector = jefaturaSector;
    }

    public String getZonaEscolar() {
        return zonaEscolar;
    }

    public void setZonaEscolar(String zonaEscolar) {
        this.zonaEscolar = zonaEscolar;
    }

    public String getDireccionRegional() {
        return direccionRegional;
    }

    public void setDireccionRegional(String direccionRegional) {
        this.direccionRegional = direccionRegional;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
 
 
}

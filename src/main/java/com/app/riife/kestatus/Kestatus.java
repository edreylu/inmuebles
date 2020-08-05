/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.kestatus;


/**
 *
 * @author Admin
 */
public class Kestatus {
 private int idEstatus;
 private String estatus;
 private String colorHex;
 private String explicacion;
 private String clave;

    public Kestatus(int idEstatus, String estatus, String colorHex, String explicacion, String clave) {
        this.idEstatus = idEstatus;
        this.estatus = estatus;
        this.colorHex = colorHex;
        this.explicacion = explicacion;
        this.clave = clave;
    }

    public Kestatus() {
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
 
 
 
}

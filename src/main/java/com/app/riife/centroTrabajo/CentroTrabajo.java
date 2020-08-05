/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.centroTrabajo;


/**
 *
 * @author Admin
 */
public class CentroTrabajo {
    private int idCentroTrabajo;
    private String claveCct;
    private String turno;
    private String cp;
    private String telefono;

    public CentroTrabajo(int idCentroTrabajo, String claveCct, String turno, String cp, String telefono) {
        this.idCentroTrabajo = idCentroTrabajo;
        this.claveCct = claveCct;
        this.turno = turno;
        this.cp = cp;
        this.telefono = telefono;
    }

    public CentroTrabajo() {
    }

    public int getIdCentroTrabajo() {
        return idCentroTrabajo;
    }

    public void setIdCentroTrabajo(int idCentroTrabajo) {
        this.idCentroTrabajo = idCentroTrabajo;
    }

    public String getClaveCct() {
        return claveCct;
    }

    public void setClaveCct(String claveCct) {
        this.claveCct = claveCct;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}

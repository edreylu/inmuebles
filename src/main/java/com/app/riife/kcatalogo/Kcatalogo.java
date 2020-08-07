/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.kcatalogo;

import com.app.riife.cuestionario.Cuestionario;

/**
 *
 * @author Admin
 */
public class Kcatalogo {
    private Cuestionario cuestionario;
    private String catalogo;
    private int claveCatalogo;
    private String descripCorta;
    private String descripcion;
    private String observaciones;
    private int idEstatus;
    private String llave;

    public Kcatalogo(Cuestionario cuestionario, String catalogo, int claveCatalogo, String descripCorta, String descripcion, String observaciones, int idEstatus, String llave) {
        this.cuestionario = cuestionario;
        this.catalogo = catalogo;
        this.claveCatalogo = claveCatalogo;
        this.descripCorta = descripCorta;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.idEstatus = idEstatus;
        this.llave = llave;
    }

    public Kcatalogo() {
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    public int getClaveCatalogo() {
        return claveCatalogo;
    }

    public void setClaveCatalogo(int claveCatalogo) {
        this.claveCatalogo = claveCatalogo;
    }

    public String getDescripCorta() {
        return descripCorta;
    }

    public void setDescripCorta(String descripCorta) {
        this.descripCorta = descripCorta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }
    
    
}

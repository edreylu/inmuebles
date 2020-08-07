/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.subCapitulo;

import com.app.riife.cuestionario.Cuestionario;

/**
 *
 * @author Admin
 */
public class SubCapitulo {
 private Cuestionario cuestionario = new Cuestionario();
 private Integer idSubCapitulo;
 private String subCapitulo;
 private int idEstatus;

    public SubCapitulo(Integer idSubCapitulo, String subCapitulo, int idEstatus) {
        this.idSubCapitulo = idSubCapitulo;
        this.subCapitulo = subCapitulo;
        this.idEstatus = idEstatus;
    }

    public SubCapitulo() {
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public Integer getIdSubCapitulo() {
        return idSubCapitulo;
    }

    public void setIdSubCapitulo(Integer idSubCapitulo) {
        this.idSubCapitulo = idSubCapitulo;
    }

    public String getSubCapitulo() {
        return subCapitulo;
    }

    public void setSubCapitulo(String subCapitulo) {
        this.subCapitulo = subCapitulo;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }
 
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.cicloEscolar;


/**
 *
 * @author Admin
 */
public class CicloEscolar {
    private int idCicloEscolar;
    private int idRevision;
    private String cicloEscolar;
    private String fechaInicial;
    private String fechaFinal;
    private int idEstatus;

    public CicloEscolar(int idCicloEscolar, int idRevision, String cicloEscolar, String fechaInicial, String fechaFinal, int idEstatus) {
        this.idCicloEscolar = idCicloEscolar;
        this.idRevision = idRevision;
        this.cicloEscolar = cicloEscolar;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.idEstatus = idEstatus;
    }
    
    public CicloEscolar(){
    
    }

    public int getIdCicloEscolar() {
        return idCicloEscolar;
    }

    public void setIdCicloEscolar(int idCicloEscolar) {
        this.idCicloEscolar = idCicloEscolar;
    }

    public int getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(int idRevision) {
        this.idRevision = idRevision;
    }

    public String getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(String cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }
    
    
}

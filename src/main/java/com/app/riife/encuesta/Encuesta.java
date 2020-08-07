/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.encuesta;

import com.app.riife.usuario.Usuario;
import java.util.Date;
/**
 *
 * @author Admin
 */
public class Encuesta {
 private Integer idCuestionario;
 private String cuestionario;
 private Date fechaRegistro;
 private String fechaRegistroStr;
 private Usuario usuarioRegistro = new Usuario();
 private String fechaModif;
 private Usuario usuarioModif = new Usuario();
 private int idEstatus;

    public Encuesta(Integer idCuestionario, String cuestionario, Date fechaRegistro, String fechaRegistroStr, String fechaModif, int idEstatus) {
        this.idCuestionario = idCuestionario;
        this.cuestionario = cuestionario;
        this.fechaRegistro = fechaRegistro;
        this.fechaRegistroStr = fechaRegistroStr;
        this.fechaModif = fechaModif;
        this.idEstatus = idEstatus;
    }

    public Encuesta() {
    }

    public Integer getIdCuestionario() {
        return idCuestionario;
    }

    public void setIdCuestionario(Integer idCuestionario) {
        this.idCuestionario = idCuestionario;
    }

    public String getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(String cuestionario) {
        this.cuestionario = cuestionario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaRegistroStr() {
        return fechaRegistroStr;
    }

    public void setFechaRegistroStr(String fechaRegistroStr) {
        this.fechaRegistroStr = fechaRegistroStr;
    }

    public Usuario getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(Usuario usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(String fechaModif) {
        this.fechaModif = fechaModif;
    }

    public Usuario getUsuarioModif() {
        return usuarioModif;
    }

    public void setUsuarioModif(Usuario usuarioModif) {
        this.usuarioModif = usuarioModif;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }
 
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.capitulo;

import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.usuario.Usuario;
import java.util.Date;

/**
 *
 * @author Admin
 */

public class Capitulo {
 private Cuestionario cuestionario = new Cuestionario();
 private Integer idCapitulo;
 private String capitulo;
 private int idEstatus;
 private Date fechaRegistro;
 private String fechaRegistroStr;
 private Usuario usuarioRegistro = new Usuario();
 private String fechaModif;
 private Usuario usuarioModif = new Usuario();

    public Capitulo(Integer idCapitulo, String capitulo, int idEstatus, Date fechaRegistro, String fechaRegistroStr, String fechaModif) {
        this.idCapitulo = idCapitulo;
        this.capitulo = capitulo;
        this.idEstatus = idEstatus;
        this.fechaRegistro = fechaRegistro;
        this.fechaRegistroStr = fechaRegistroStr;
        this.fechaModif = fechaModif;
    }

    public Capitulo() {
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public Integer getIdCapitulo() {
        return idCapitulo;
    }

    public void setIdCapitulo(Integer idCapitulo) {
        this.idCapitulo = idCapitulo;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
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
 
 
    
  
}

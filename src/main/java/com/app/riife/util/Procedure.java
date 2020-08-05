/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.util;


/**
 *
 * @author Edward Reyes
 */
public class Procedure {
    
    private String Mensaje;
    private Integer error;

    public Procedure(String Mensaje, Integer error) {
        this.Mensaje = Mensaje;
        this.error = error;
    }

    public Procedure() {
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }
    
}

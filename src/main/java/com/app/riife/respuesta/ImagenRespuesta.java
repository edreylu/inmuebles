/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.respuesta;


/**
 *
 * @author Admin
 */
public class ImagenRespuesta {
    private int idRespuesta;
    private int idImagen;
    private String nombreArchivo;
    private String tipoArchivo;

    public ImagenRespuesta(int idRespuesta, int idImagen, String nombreArchivo, String tipoArchivo) {
        this.idRespuesta = idRespuesta;
        this.idImagen = idImagen;
        this.nombreArchivo = nombreArchivo;
        this.tipoArchivo = tipoArchivo;
    }

    public ImagenRespuesta() {
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }
    
    
}

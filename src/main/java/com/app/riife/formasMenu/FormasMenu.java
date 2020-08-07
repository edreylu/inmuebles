/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.formasMenu;


/**
 *
 * @author usuario
 */

public class FormasMenu {
    private int noFormaMenu;
    private String titulo;
    private String url;
    private String icono;
    private int noTipoPadre;
    private int noFormaPadre;
    private String nombrePapa;
    private String iconoPapa;
    private boolean menuSeleccionado;
    private String selec;
    private String clavebuscada;

    public FormasMenu(int noFormaMenu, String titulo, String url, String icono, int noTipoPadre, int noFormaPadre, String nombrePapa, String iconoPapa, boolean menuSeleccionado, String selec, String clavebuscada) {
        this.noFormaMenu = noFormaMenu;
        this.titulo = titulo;
        this.url = url;
        this.icono = icono;
        this.noTipoPadre = noTipoPadre;
        this.noFormaPadre = noFormaPadre;
        this.nombrePapa = nombrePapa;
        this.iconoPapa = iconoPapa;
        this.menuSeleccionado = menuSeleccionado;
        this.selec = selec;
        this.clavebuscada = clavebuscada;
    }

    public FormasMenu() {
    }

    public int getNoFormaMenu() {
        return noFormaMenu;
    }

    public void setNoFormaMenu(int noFormaMenu) {
        this.noFormaMenu = noFormaMenu;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public int getNoTipoPadre() {
        return noTipoPadre;
    }

    public void setNoTipoPadre(int noTipoPadre) {
        this.noTipoPadre = noTipoPadre;
    }

    public int getNoFormaPadre() {
        return noFormaPadre;
    }

    public void setNoFormaPadre(int noFormaPadre) {
        this.noFormaPadre = noFormaPadre;
    }

    public String getNombrePapa() {
        return nombrePapa;
    }

    public void setNombrePapa(String nombrePapa) {
        this.nombrePapa = nombrePapa;
    }

    public String getIconoPapa() {
        return iconoPapa;
    }

    public void setIconoPapa(String iconoPapa) {
        this.iconoPapa = iconoPapa;
    }

    public boolean isMenuSeleccionado() {
        return menuSeleccionado;
    }

    public void setMenuSeleccionado(boolean menuSeleccionado) {
        this.menuSeleccionado = menuSeleccionado;
    }

    public String getSelec() {
        return selec;
    }

    public void setSelec(String selec) {
        this.selec = selec;
    }

    public String getClavebuscada() {
        return clavebuscada;
    }

    public void setClavebuscada(String clavebuscada) {
        this.clavebuscada = clavebuscada;
    }
    
    
    
}

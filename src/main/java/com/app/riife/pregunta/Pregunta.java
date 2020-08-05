/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.pregunta;

import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.capitulo.Capitulo;
import com.app.riife.subCapitulo.SubCapitulo;
import com.app.riife.usuario.Usuario;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Admin
 */
public class Pregunta {
    private Cuestionario cuestionario = new Cuestionario();
    private int idPregunta;
    private String pregunta;
    private int idEstatus;
    private Capitulo capitulo = new Capitulo();
    private SubCapitulo subCapitulo = new SubCapitulo();
    private int ordenMostrar;
    private String inciso;
    private String opcion;
    private String instruccionesLlenado;
    private String opcionMultiple;
    private String enCatalogo = "N";
    private String catalogo;
    private Integer claveCatalogo;
    private String especificarxCatalogo = "N";
    private String tipoDeDatoxCatalogo;
    private Integer longitudMaximaxCatalogo;
    private Integer decimalesLongMaxCat;
    private String subirImagen = "N";
    private String otroEspecificar = "N";
    private String tipoDeDatoOtro;
    private Integer longitudMaximaOtro;
    private Integer decimalesLongMaxOtro;
    private Integer idCuestionarioRef;
    private Integer idPreguntaRef;
    private Usuario usuarioRegistro = new Usuario();
    private Date fechaRegistro;
    private Usuario usuarioModif = new Usuario();
    private Date fechaModif;
    private String especificarPor;
    private String cabeceraPregunta;

    public Pregunta(int idPregunta, String pregunta, int idEstatus, int ordenMostrar, String inciso, String opcion, String instruccionesLlenado, String opcionMultiple, String catalogo, Integer claveCatalogo, String tipoDeDatoxCatalogo, Integer longitudMaximaxCatalogo, Integer decimalesLongMaxCat, String tipoDeDatoOtro, Integer longitudMaximaOtro, Integer decimalesLongMaxOtro, Integer idCuestionarioRef, Integer idPreguntaRef, Date fechaRegistro, Date fechaModif, String especificarPor, String cabeceraPregunta) {
        this.idPregunta = idPregunta;
        this.pregunta = pregunta;
        this.idEstatus = idEstatus;
        this.ordenMostrar = ordenMostrar;
        this.inciso = inciso;
        this.opcion = opcion;
        this.instruccionesLlenado = instruccionesLlenado;
        this.opcionMultiple = opcionMultiple;
        this.catalogo = catalogo;
        this.claveCatalogo = claveCatalogo;
        this.tipoDeDatoxCatalogo = tipoDeDatoxCatalogo;
        this.longitudMaximaxCatalogo = longitudMaximaxCatalogo;
        this.decimalesLongMaxCat = decimalesLongMaxCat;
        this.tipoDeDatoOtro = tipoDeDatoOtro;
        this.longitudMaximaOtro = longitudMaximaOtro;
        this.decimalesLongMaxOtro = decimalesLongMaxOtro;
        this.idCuestionarioRef = idCuestionarioRef;
        this.idPreguntaRef = idPreguntaRef;
        this.fechaRegistro = fechaRegistro;
        this.fechaModif = fechaModif;
        this.especificarPor = especificarPor;
        this.cabeceraPregunta = cabeceraPregunta;
    }

    public Pregunta() {
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(int idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Capitulo getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(Capitulo capitulo) {
        this.capitulo = capitulo;
    }

    public SubCapitulo getSubCapitulo() {
        return subCapitulo;
    }

    public void setSubCapitulo(SubCapitulo subCapitulo) {
        this.subCapitulo = subCapitulo;
    }

    public int getOrdenMostrar() {
        return ordenMostrar;
    }

    public void setOrdenMostrar(int ordenMostrar) {
        this.ordenMostrar = ordenMostrar;
    }

    public String getInciso() {
        return inciso;
    }

    public void setInciso(String inciso) {
        this.inciso = inciso;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getInstruccionesLlenado() {
        return instruccionesLlenado;
    }

    public void setInstruccionesLlenado(String instruccionesLlenado) {
        this.instruccionesLlenado = instruccionesLlenado;
    }

    public String getOpcionMultiple() {
        return opcionMultiple;
    }

    public void setOpcionMultiple(String opcionMultiple) {
        this.opcionMultiple = opcionMultiple;
    }

    public String getEnCatalogo() {
        return enCatalogo;
    }

    public void setEnCatalogo(String enCatalogo) {
        this.enCatalogo = enCatalogo;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    public Integer getClaveCatalogo() {
        return claveCatalogo;
    }

    public void setClaveCatalogo(Integer claveCatalogo) {
        this.claveCatalogo = claveCatalogo;
    }

    public String getEspecificarxCatalogo() {
        return especificarxCatalogo;
    }

    public void setEspecificarxCatalogo(String especificarxCatalogo) {
        this.especificarxCatalogo = especificarxCatalogo;
    }

    public String getTipoDeDatoxCatalogo() {
        return tipoDeDatoxCatalogo;
    }

    public void setTipoDeDatoxCatalogo(String tipoDeDatoxCatalogo) {
        this.tipoDeDatoxCatalogo = tipoDeDatoxCatalogo;
    }

    public Integer getLongitudMaximaxCatalogo() {
        return longitudMaximaxCatalogo;
    }

    public void setLongitudMaximaxCatalogo(Integer longitudMaximaxCatalogo) {
        this.longitudMaximaxCatalogo = longitudMaximaxCatalogo;
    }

    public Integer getDecimalesLongMaxCat() {
        return decimalesLongMaxCat;
    }

    public void setDecimalesLongMaxCat(Integer decimalesLongMaxCat) {
        this.decimalesLongMaxCat = decimalesLongMaxCat;
    }

    public String getSubirImagen() {
        return subirImagen;
    }

    public void setSubirImagen(String subirImagen) {
        this.subirImagen = subirImagen;
    }

    public String getOtroEspecificar() {
        return otroEspecificar;
    }

    public void setOtroEspecificar(String otroEspecificar) {
        this.otroEspecificar = otroEspecificar;
    }

    public String getTipoDeDatoOtro() {
        return tipoDeDatoOtro;
    }

    public void setTipoDeDatoOtro(String tipoDeDatoOtro) {
        this.tipoDeDatoOtro = tipoDeDatoOtro;
    }

    public Integer getLongitudMaximaOtro() {
        return longitudMaximaOtro;
    }

    public void setLongitudMaximaOtro(Integer longitudMaximaOtro) {
        this.longitudMaximaOtro = longitudMaximaOtro;
    }

    public Integer getDecimalesLongMaxOtro() {
        return decimalesLongMaxOtro;
    }

    public void setDecimalesLongMaxOtro(Integer decimalesLongMaxOtro) {
        this.decimalesLongMaxOtro = decimalesLongMaxOtro;
    }

    public Integer getIdCuestionarioRef() {
        return idCuestionarioRef;
    }

    public void setIdCuestionarioRef(Integer idCuestionarioRef) {
        this.idCuestionarioRef = idCuestionarioRef;
    }

    public Integer getIdPreguntaRef() {
        return idPreguntaRef;
    }

    public void setIdPreguntaRef(Integer idPreguntaRef) {
        this.idPreguntaRef = idPreguntaRef;
    }

    public Usuario getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(Usuario usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario getUsuarioModif() {
        return usuarioModif;
    }

    public void setUsuarioModif(Usuario usuarioModif) {
        this.usuarioModif = usuarioModif;
    }

    public Date getFechaModif() {
        return fechaModif;
    }

    public void setFechaModif(Date fechaModif) {
        this.fechaModif = fechaModif;
    }

    public String getEspecificarPor() {
        return especificarPor;
    }

    public void setEspecificarPor(String especificarPor) {
        this.especificarPor = especificarPor;
    }

    public String getCabeceraPregunta() {
        return cabeceraPregunta;
    }

    public void setCabeceraPregunta(String cabeceraPregunta) {
        this.cabeceraPregunta = cabeceraPregunta;
    }
    
    
    
    
    public static Pregunta preguntaAddAjustes(Pregunta pregunta){
        
        if (Objects.equals(pregunta.getEspecificarPor(), "C"))
            pregunta.setEspecificarxCatalogo("S");
         else if (Objects.equals(pregunta.getEspecificarPor(), "O"))
            pregunta.setOtroEspecificar("S");
        
        return pregunta;
    }

    public static Pregunta preguntaEditAjustes(Pregunta pregunta){
        pregunta.setEspecificarPor(Objects.isNull(pregunta.getEspecificarPor()) ? "O" : pregunta.getEspecificarPor());
        pregunta.setOtroEspecificar("N");
        pregunta.setEspecificarxCatalogo("N");
        if (Objects.equals(pregunta.getEspecificarPor(),"C")) {
            pregunta.setEspecificarxCatalogo("S");
        } else if (Objects.equals(pregunta.getEspecificarPor(),"O")) {
            pregunta.setOtroEspecificar("S");
        }

        return pregunta;
    }
    
    public static Pregunta preguntaHtml(){
    return new Pregunta();
    }

    @Override
    public String toString() {
        return "Pregunta{" + "cuestionario=" + cuestionario + ", idPregunta=" + idPregunta + ", pregunta=" + pregunta + ", idEstatus=" + idEstatus + ", capitulo=" + capitulo + ", subCapitulo=" + subCapitulo + ", ordenMostrar=" + ordenMostrar + ", inciso=" + inciso + ", opcion=" + opcion + ", instruccionesLlenado=" + instruccionesLlenado + ", opcionMultiple=" + opcionMultiple + ", enCatalogo=" + enCatalogo + ", catalogo=" + catalogo + ", claveCatalogo=" + claveCatalogo + ", especificarxCatalogo=" + especificarxCatalogo + ", tipoDeDatoxCatalogo=" + tipoDeDatoxCatalogo + ", longitudMaximaxCatalogo=" + longitudMaximaxCatalogo + ", decimalesLongMaxCat=" + decimalesLongMaxCat + ", subirImagen=" + subirImagen + ", otroEspecificar=" + otroEspecificar + ", tipoDeDatoOtro=" + tipoDeDatoOtro + ", longitudMaximaOtro=" + longitudMaximaOtro + ", decimalesLongMaxOtro=" + decimalesLongMaxOtro + ", idCuestionarioRef=" + idCuestionarioRef + ", idPreguntaRef=" + idPreguntaRef + ", usuarioRegistro=" + usuarioRegistro + ", fechaRegistro=" + fechaRegistro + ", usuarioModif=" + usuarioModif + ", fechaModif=" + fechaModif + ", especificarPor=" + especificarPor + '}';
    }
    
    
}
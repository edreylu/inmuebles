/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.respuesta;

import com.app.riife.pregunta.Pregunta;
import com.app.riife.inmueble.Inmueble;
import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.cicloEscolar.CicloEscolar;
import com.app.riife.centroTrabajo.CentroTrabajo;
import com.app.riife.usuario.Usuario;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Respuesta {
    private int idRespuesta;
    private Cuestionario cuestionario = new Cuestionario();
    private Pregunta pregunta = new Pregunta();
    private Inmueble inmueble = new Inmueble();
    private String catalogo;
    private String claveCatalogo;
    private String respuesta;
    private String respuestaEspecifica;
    private String observaciones;
    private Date fechaRegistro;
    private String fechaRegistroStr;
    private CicloEscolar cicloEscolar = new CicloEscolar();
    private int idRevision;
    private CentroTrabajo centroTrabajo = new CentroTrabajo();
    private String claveCct;
    private String turno;
    private int aulas;
    private int laboratorios;
    private int talleres;
    private Usuario usuarioRegistro = new Usuario();
    private Usuario usuarioModif = new Usuario();
    private int operacion;
    private int noRespuesta;

    public Respuesta(int idRespuesta, String catalogo, String claveCatalogo, String respuesta, String respuestaEspecifica, String observaciones, Date fechaRegistro, String fechaRegistroStr, int idRevision, String claveCct, String turno, int aulas, int laboratorios, int talleres, int operacion, int noRespuesta) {
        this.idRespuesta = idRespuesta;
        this.catalogo = catalogo;
        this.claveCatalogo = claveCatalogo;
        this.respuesta = respuesta;
        this.respuestaEspecifica = respuestaEspecifica;
        this.observaciones = observaciones;
        this.fechaRegistro = fechaRegistro;
        this.fechaRegistroStr = fechaRegistroStr;
        this.idRevision = idRevision;
        this.claveCct = claveCct;
        this.turno = turno;
        this.aulas = aulas;
        this.laboratorios = laboratorios;
        this.talleres = talleres;
        this.operacion = operacion;
        this.noRespuesta = noRespuesta;
    }

    public Respuesta() {
    }
    
    
    public Respuesta(Respuesta respuesta) {
        this.cuestionario = respuesta.getCuestionario();
        this.pregunta = respuesta.getPregunta();
        this.inmueble = respuesta.getInmueble();
        this.cicloEscolar = respuesta.getCicloEscolar();
        this.centroTrabajo = respuesta.getCentroTrabajo();
        this.usuarioRegistro = respuesta.getUsuarioRegistro();
        this.usuarioModif = respuesta.getUsuarioModif();
        
        this.idRespuesta = respuesta.getIdRespuesta();
        this.catalogo = respuesta.getCatalogo();
        this.claveCatalogo = respuesta.getClaveCatalogo();
        this.respuesta = respuesta.getRespuesta();
        this.respuestaEspecifica = respuesta.getRespuestaEspecifica();
        this.observaciones = respuesta.getObservaciones();
        this.fechaRegistro = respuesta.getFechaRegistro();
        this.fechaRegistroStr = respuesta.getFechaRegistroStr();
        this.idRevision = respuesta.getIdRevision();
        this.claveCct = respuesta.getClaveCct();
        this.turno = respuesta.getTurno();
        this.aulas = respuesta.getAulas();
        this.laboratorios = respuesta.getLaboratorios();
        this.talleres = respuesta.getTalleres();
        this.operacion = respuesta.getOperacion();    
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public Cuestionario getCuestionario() {
        return cuestionario;
    }

    public void setCuestionario(Cuestionario cuestionario) {
        this.cuestionario = cuestionario;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    public String getClaveCatalogo() {
        return claveCatalogo;
    }

    public void setClaveCatalogo(String claveCatalogo) {
        this.claveCatalogo = claveCatalogo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuestaEspecifica() {
        return respuestaEspecifica;
    }

    public void setRespuestaEspecifica(String respuestaEspecifica) {
        this.respuestaEspecifica = respuestaEspecifica;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public CicloEscolar getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(CicloEscolar cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    public int getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(int idRevision) {
        this.idRevision = idRevision;
    }

    public CentroTrabajo getCentroTrabajo() {
        return centroTrabajo;
    }

    public void setCentroTrabajo(CentroTrabajo centroTrabajo) {
        this.centroTrabajo = centroTrabajo;
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

    public int getAulas() {
        return aulas;
    }

    public void setAulas(int aulas) {
        this.aulas = aulas;
    }

    public int getLaboratorios() {
        return laboratorios;
    }

    public void setLaboratorios(int laboratorios) {
        this.laboratorios = laboratorios;
    }

    public int getTalleres() {
        return talleres;
    }

    public void setTalleres(int talleres) {
        this.talleres = talleres;
    }

    public Usuario getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(Usuario usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Usuario getUsuarioModif() {
        return usuarioModif;
    }

    public void setUsuarioModif(Usuario usuarioModif) {
        this.usuarioModif = usuarioModif;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    public int getNoRespuesta() {
        return noRespuesta;
    }

    public void setNoRespuesta(int noRespuesta) {
        this.noRespuesta = noRespuesta;
    }

    
}
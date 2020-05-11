/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.respuesta;

import com.app.inmuebles.pregunta.Pregunta;
import com.app.inmuebles.inmueble.Inmueble;
import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.cicloEscolar.CicloEscolar;
import com.app.inmuebles.centroTrabajo.CentroTrabajo;
import com.app.inmuebles.usuario.Usuario;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Admin
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    
}
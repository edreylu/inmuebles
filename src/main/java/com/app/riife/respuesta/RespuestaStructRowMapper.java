/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.respuesta;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;

/**
 *
 * @author usuario
 */
public class RespuestaStructRowMapper {

    public Struct toStruct(Respuesta respuesta, Connection conn) throws SQLException {
        Object[] attributes = {
            respuesta.getIdRespuesta(),
            respuesta.getCuestionario().getIdCuestionario(),
            respuesta.getPregunta().getIdPregunta(),
            respuesta.getInmueble().getIdInmueble(),
            respuesta.getCatalogo(),
            respuesta.getClaveCatalogo(),
            respuesta.getRespuesta(),
            respuesta.getRespuestaEspecifica(),
            respuesta.getObservaciones(),
            respuesta.getCicloEscolar().getIdCicloEscolar(),
            respuesta.getIdRevision(),
            respuesta.getOperacion()==1?respuesta.getUsuarioRegistro().getNoUsuario():respuesta.getUsuarioModif().getNoUsuario(),
            respuesta.getOperacion()
        };        
        System.out.println(respuesta.getIdRespuesta()+","+
            respuesta.getCuestionario().getIdCuestionario()+","+
            respuesta.getPregunta().getIdPregunta()+","+
            respuesta.getInmueble().getIdInmueble()+","+
            respuesta.getCatalogo()+","+
            respuesta.getClaveCatalogo()+","+
            respuesta.getRespuesta()+","+
            respuesta.getRespuestaEspecifica()+","+
            respuesta.getObservaciones()+","+
            respuesta.getCicloEscolar().getIdCicloEscolar()+","+
            respuesta.getIdRevision()+","+
            (respuesta.getOperacion()==1?respuesta.getUsuarioRegistro().getNoUsuario():respuesta.getUsuarioModif().getNoUsuario())+","+
            respuesta.getOperacion());
        return conn.createStruct("ODBRESPUESTA", attributes);
    }

}

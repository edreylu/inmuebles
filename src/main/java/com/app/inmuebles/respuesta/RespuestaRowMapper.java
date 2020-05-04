/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.respuesta;

import com.app.inmuebles.respuesta.Respuesta;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author usuario
 */
public class RespuestaRowMapper implements RowMapper<Respuesta> {

    @Override
    public Respuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
        Respuesta respuesta = new Respuesta();

        respuesta.setIdRespuesta(rs.getInt("idrespuesta"));
        respuesta.getCuestionario().setIdCuestionario(rs.getInt("idcuestionario"));
        respuesta.getPregunta().setIdPregunta(rs.getInt("idpregunta"));
        respuesta.getInmueble().setIdInmueble(rs.getInt("idinmueble"));
        respuesta.setCatalogo(rs.getString("catalogo"));
        respuesta.setClaveCatalogo(rs.getString("clavecatalogo"));
        respuesta.setRespuesta(rs.getString("respuesta"));
        respuesta.setRespuestaEspecifica(rs.getString("respuestaespecifica"));
        respuesta.setObservaciones(rs.getString("observaciones"));
        respuesta.getCicloEscolar().setIdCicloEscolar(rs.getInt("idcicloescolar"));
        respuesta.setIdRevision(rs.getInt("idrevision"));
        respuesta.getUsuarioRegistro().setNoUsuario(rs.getInt("noUsuarioRegistro"));
        respuesta.getUsuarioModif().setNoUsuario(rs.getInt("noUsuarioModif"));

        return respuesta;

    }

}

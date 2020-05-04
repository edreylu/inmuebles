/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.respuesta;

import com.app.inmuebles.util.Procedure;
import com.app.inmuebles.respuesta.Respuesta;
import com.app.inmuebles.respuesta.RespuestaRowMapper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Edward Reyes
 */
@Repository
public class RespuestaDAOImpl implements RespuestaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    List lista = null;
    String sql;
    int estatus = 1;

    @Override
    public Procedure ActRespuesta(List<Respuesta> respuestas) {
        //Respuesta respuesta = new Respuesta();
        //RespuestaStructRowMapper mapper = new RespuestaStructRowMapper();
        
        //OracleConnection connectionOracle = null;
        /*if (connection.isWrapperFor(OracleConnection.class)) {
                connectionOracle = connection.unwrap(OracleConnection.class);
            }
            Struct[] structValues = new Struct[respuestas.size()];
            for (int i = 0; i < structValues.length; i++) {
                structValues[i] = mapper.toStruct(respuestas.get(i), connection);
            }
            Array listaRespuestas = connectionOracle.createARRAY("TDBRESPUESTA", structValues);
            */
        respuestas.forEach((res)->System.out.println(res.toString()));
         Procedure proc = null;
         CallableStatement cstmt = null;
         for (Respuesta respuesta: respuestas){
             proc = new Procedure();
        try(Connection connection = jdbcTemplate.getDataSource().getConnection()){
            
            cstmt = connection.prepareCall("{call pdbActRespuesta(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cstmt.registerOutParameter(1, Types.NUMERIC);
            cstmt.registerOutParameter(2, Types.VARCHAR);
            cstmt.setInt(3, respuesta.getIdRespuesta());
            cstmt.setInt(4, respuesta.getCuestionario().getIdCuestionario());
            cstmt.setInt(5, respuesta.getPregunta().getIdPregunta());
            cstmt.setInt(6, respuesta.getInmueble().getIdInmueble());
            cstmt.setString(7, respuesta.getCatalogo());
            cstmt.setString(8, respuesta.getClaveCatalogo());
            cstmt.setString(9, respuesta.getRespuesta());
            cstmt.setString(10, respuesta.getRespuestaEspecifica());
            cstmt.setString(11, respuesta.getObservaciones());
            cstmt.setInt(12, respuesta.getCicloEscolar().getIdCicloEscolar());
            cstmt.setInt(13, respuesta.getCicloEscolar().getIdRevision());
            cstmt.setInt(14, respuesta.getOperacion()==1?respuesta.getUsuarioRegistro().getNoUsuario()
                    :respuesta.getUsuarioModif().getNoUsuario());
            cstmt.setInt(15, respuesta.getOperacion());
            
            cstmt.execute();

            proc.setError(cstmt.getInt(1));
            proc.setMensaje(cstmt.getString(2));
            
            //System.out.println("DAO setError*********** : " + proc.getError());
            //System.out.println("DAO Mensaje*********** : " + proc.getMensaje());
        } catch (SQLException ex) {
            Logger.getLogger(RespuestaDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
        return proc;
    }

    @Override
    public List<Respuesta> getRegistrosRespuestas(int idCuestionario, int noUsuario) {
        sql = "select re.IDRESPUESTA,\n"
                + "re.IDCUESTIONARIO,\n"
                + "(select cu.cuestionario from cuestionario cu where cu.idcuestionario = re.idcuestionario) cuestionario,\n"
                + "re.IDPREGUNTA,\n"
                + "(select pr.pregunta from pregunta pr where pr.idpregunta = re.idpregunta) pregunta,\n"
                + "re.IDINMUEBLE,\n"
                + "(select inm.claveinmueble from inmueble inm where inm.idinmueble = re.idinmueble) inmueble,\n"
                + "re.CATALOGO,\n"
                + "re.CLAVECATALOGO,\n"
                + "re.RESPUESTA,\n"
                + "re.RESPUESTAESPECIFICA,\n"
                + "re.OBSERVACIONES,\n"
                + "re.FECHAREGISTRO,\n"
                + "re.IDCICLOESCOLAR,\n"
                + "re.IDREVISION,\n"
                + "re.IDCENTROTRABAJO,\n"
                + "re.CLAVECCT,\n"
                + "re.TURNO,\n"
                + "re.AULAS,\n"
                + "re.LABORATORIOS,\n"
                + "re.TALLERES,\n"
                + "re.NOUSUARIOREGISTRO,\n"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = re.nousuarioregistro) nomusuarioregistro,\n"
                + "re.NOUSUARIOMODIF,\n"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = re.nousuariomodif) nomusuariomodif,\n"
                + "re.FECHAMODIF,\n"
                + "re.TIPODEDATORESP,\n"
                + "re.LONGITUDMAXIMARESP,\n"
                + "re.DECIMALESLONGMAXRESP,\n"
                + "re.TIPODEDATORESPESPECIFICA,\n"
                + "re.LONGITUDMAXIMARESPESPECIFICA,\n"
                + "re.DECIMALESLONGMAXRESPESPECIFICA from respuesta re where re.idcuestionario=? and re.NOUSUARIOREGISTRO = ?";
        try {
            lista = jdbcTemplate.query(sql, new Object[]{idCuestionario, noUsuario}, new RespuestaRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }

        return lista;
    }

    @Override
    public int countResponses(int idCuestionario, int noUsuario) {
        int valor = 0;
        sql = "SELECT count(1) from respuesta where idcuestionario=? and NOUSUARIOREGISTRO = ? ";
        try {
            valor = jdbcTemplate.queryForObject(sql, new Object[]{idCuestionario, noUsuario}, Integer.class);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        System.out.println("com.app.inmuebles.dao.RespuestaDAO.countResponses()" + valor);
        return valor;
    }

    @Override
    public Respuesta getRespuesta(int idCuestionario, int noUsuario, int idPregunta) {
        Respuesta respuesta = new Respuesta();
       sql = "select re.IDRESPUESTA,\n"
                + "re.IDCUESTIONARIO,\n"
                + "(select cu.cuestionario from cuestionario cu where cu.idcuestionario = re.idcuestionario) cuestionario,\n"
                + "re.IDPREGUNTA,\n"
                + "(select pr.pregunta from pregunta pr where pr.idpregunta = re.idpregunta) pregunta,\n"
                + "re.IDINMUEBLE,\n"
                + "(select inm.claveinmueble from inmueble inm where inm.idinmueble = re.idinmueble) inmueble,\n"
                + "re.CATALOGO,\n"
                + "re.CLAVECATALOGO,\n"
                + "re.RESPUESTA,\n"
                + "re.RESPUESTAESPECIFICA,\n"
                + "re.OBSERVACIONES,\n"
                + "re.FECHAREGISTRO,\n"
                + "re.IDCICLOESCOLAR,\n"
                + "re.IDREVISION,\n"
                + "re.IDCENTROTRABAJO,\n"
                + "re.CLAVECCT,\n"
                + "re.TURNO,\n"
                + "re.AULAS,\n"
                + "re.LABORATORIOS,\n"
                + "re.TALLERES,\n"
                + "re.NOUSUARIOREGISTRO,\n"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = re.nousuarioregistro) nomusuarioregistro,\n"
                + "re.NOUSUARIOMODIF,\n"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = re.nousuariomodif) nomusuariomodif,\n"
                + "re.FECHAMODIF,\n"
                + "re.TIPODEDATORESP,\n"
                + "re.LONGITUDMAXIMARESP,\n"
                + "re.DECIMALESLONGMAXRESP,\n"
                + "re.TIPODEDATORESPESPECIFICA,\n"
                + "re.LONGITUDMAXIMARESPESPECIFICA,\n"
                + "re.DECIMALESLONGMAXRESPESPECIFICA from respuesta re where re.idcuestionario=? and re.NOUSUARIOREGISTRO = ? and re.IDPREGUNTA = ?";
        try {
            respuesta = jdbcTemplate.queryForObject(sql, new Object[]{idCuestionario, noUsuario, idPregunta}, new RespuestaRowMapper());
        } catch (DataAccessException e) {
            System.err.println(e);
        }

        return respuesta;
    }
}

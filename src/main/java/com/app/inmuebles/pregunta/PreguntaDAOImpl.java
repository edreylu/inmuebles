/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.pregunta;

import com.app.inmuebles.pregunta.Pregunta;
import com.app.inmuebles.pregunta.PreguntaRowMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usuario
 */
@Repository
public class PreguntaDAOImpl implements PreguntaDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    List lista = null;
    String sql = "";
    int estatus = 1;

    @Override
    public List<Pregunta> getRegistros() {
        sql = "select pr.idcuestionario,\n"
                + "(select cu.cuestionario from cuestionario cu where cu.idcuestionario = pr.idcuestionario) cuestionario,\n"
                + "pr.idpregunta,\n"
                + "pr.pregunta,\n"
                + "pr.idestatus,\n"
                + "pr.idcapitulo,\n"
                + "(select ca.capitulo from capitulo ca where ca.idcapitulo = pr.idcapitulo) capitulo,\n"
                + "pr.idsubcapitulo,\n"
                + "(select sc.subcapitulo from subcapitulo sc where sc.idsubcapitulo = pr.idsubcapitulo) subcapitulo,\n"
                + "pr.ordenmostrar,\n"
                + "pr.inciso,\n"
                + "pr.opcion,\n"
                + "pr.instruccionesllenado,\n"
                + "pr.opcionmultiple,\n"
                + "pr.encatalogo,\n"
                + "pr.catalogo,\n"
                + "pr.clavecatalogo,\n"
                + "pr.especificarxcatalogo,\n"
                + "pr.tipodedatoxcatalogo,\n"
                + "pr.longitudmaximaxcatalogo,\n"
                + "pr.decimaleslongmaxcat,\n"
                + "pr.subirimagen,\n"
                + "pr.otroespecificar,\n"
                + "pr.tipodedatootro,\n"
                + "pr.longitudmaximaotro,\n"
                + "pr.decimaleslongmaxotro,\n"
                + "pr.idcuestionarioref,\n"
                + "pr.idpreguntaref,\n"
                + "pr.nousuarioregistro,\n"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = pr.nousuarioregistro) nomusuarioregistro,\n"
                + "pr.fecharegistro,\n"
                + "pr.nousuariomodif,\n"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = pr.nousuariomodif) nomusuariomodif,\n"
                + "pr.fechamodif from pregunta pr\n"
                + "               order by 1";
        try {
            lista = jdbcTemplate.query(sql, new PreguntaRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return lista;
    }

    @Override
    public int addPregunta(Pregunta pr) {
        int valor = 0;
        int idPregunta = jdbcTemplate.queryForObject("select nvl(max(idpregunta),0)+ 1 from pregunta", Integer.class);
        pr.setIdPregunta(idPregunta);
        sql = "INSERT INTO pregunta (idcuestionario,\n"
                + "idpregunta,\n"
                + "pregunta,\n "
                + "idestatus,\n"
                + "idcapitulo,\n"
                + "idsubcapitulo,\n"
                + "ordenmostrar,\n"
                + "inciso,\n"
                + "opcion,\n"
                + "instruccionesllenado,\n"
                + "opcionmultiple,\n"
                + "encatalogo,\n"
                + "catalogo,\n"
                + "clavecatalogo,\n"
                + "especificarxcatalogo,\n"
                + "tipodedatoxcatalogo,\n"
                + "longitudmaximaxcatalogo,\n"
                + "decimaleslongmaxcat,\n"
                + "subirimagen,\n"
                + "otroespecificar,\n"
                + "tipodedatootro,\n"
                + "longitudmaximaotro,\n"
                + "decimaleslongmaxotro,\n"
                + "idcuestionarioref,\n"
                + "idpreguntaref,\n"
                + "nousuarioregistro,\n"
                + "fecharegistro,\n"
                + "nousuariomodif,\n"
                + "fechamodif) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";
        try {
            valor = jdbcTemplate.update(sql,
                    pr.getCuestionario().getIdCuestionario(),
                    pr.getIdPregunta(),
                    pr.getPregunta().toUpperCase(),
                    1,
                    pr.getCapitulo().getIdCapitulo(),
                    pr.getSubCapitulo().getIdSubCapitulo(),
                    pr.getOrdenMostrar(),
                    pr.getInciso(),
                    pr.getOpcion(),
                    pr.getInstruccionesLlenado() != null ? pr.getInstruccionesLlenado().toUpperCase() : pr.getInstruccionesLlenado(),
                    pr.getOpcionMultiple(),
                    pr.getEnCatalogo(),
                    pr.getCatalogo(),
                    pr.getClaveCatalogo(),
                    pr.getEspecificarxCatalogo(),
                    pr.getTipoDeDatoxCatalogo(),
                    pr.getLongitudMaximaxCatalogo(),
                    pr.getDecimalesLongMaxCat(),
                    pr.getSubirImagen(),
                    pr.getOtroEspecificar(),
                    pr.getTipoDeDatoOtro(),
                    pr.getLongitudMaximaOtro(),
                    pr.getDecimalesLongMaxOtro(),
                    null,
                    null,
                    pr.getUsuarioRegistro().getNoUsuario(),
                    null,
                    null);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public int editPregunta(Pregunta pr) {
        int valor = 0;
        sql = "UPDATE pregunta\n "
                + "SET idcuestionario = ?,\n "
                + "pregunta= ?,\n "
                + "idcapitulo= ?,\n"
                + "idsubcapitulo= ?,\n"
                + "ordenmostrar= ?,\n"
                + "inciso= ?,\n"
                + "opcion= ?,\n"
                + "instruccionesllenado= ?,\n"
                + "opcionmultiple= ?,\n"
                + "encatalogo= ?,\n"
                + "catalogo= ?,\n"
                + "clavecatalogo= ?,\n"
                + "especificarxcatalogo= ?,\n"
                + "tipodedatoxcatalogo= ?,\n"
                + "longitudmaximaxcatalogo= ?,\n"
                + "decimaleslongmaxcat= ?,\n"
                + "subirimagen= ?,\n"
                + "otroespecificar= ?,\n"
                + "tipodedatootro= ?,\n"
                + "longitudmaximaotro= ?,\n"
                + "decimaleslongmaxotro= ?,\n"
                + "idcuestionarioref= ?,\n"
                + "idpreguntaref= ?,\n"
                + "nousuariomodif= ?,\n"
                + "fechamodif= SYSDATE \n"
                + "WHERE idpregunta = ? ";
        try {
            valor = jdbcTemplate.update(sql,
                    pr.getCuestionario().getIdCuestionario(),
                    pr.getPregunta().toUpperCase(),
                    pr.getCapitulo().getIdCapitulo(),
                    pr.getSubCapitulo().getIdSubCapitulo(),
                    pr.getOrdenMostrar(),
                    pr.getInciso(),
                    pr.getOpcion(),
                    pr.getInstruccionesLlenado() != null ? pr.getInstruccionesLlenado().toUpperCase() : pr.getInstruccionesLlenado(),
                    pr.getOpcionMultiple(),
                    pr.getEnCatalogo(),
                    pr.getCatalogo(),
                    pr.getClaveCatalogo(),
                    pr.getEspecificarxCatalogo(),
                    pr.getTipoDeDatoxCatalogo(),
                    pr.getLongitudMaximaxCatalogo(),
                    pr.getDecimalesLongMaxCat(),
                    pr.getSubirImagen(),
                    pr.getOtroEspecificar(),
                    pr.getTipoDeDatoOtro(),
                    pr.getLongitudMaximaOtro(),
                    pr.getDecimalesLongMaxOtro(),
                    null,
                    null,
                    pr.getUsuarioModif().getNoUsuario(),
                    pr.getIdPregunta());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public Pregunta getPregunta(int id) {

        Pregunta pr = null;
        sql = "select pr.idcuestionario,\n"
                + "(select cu.cuestionario from cuestionario cu where cu.idcuestionario = pr.idcuestionario) cuestionario,\n"
                + "pr.idpregunta,\n"
                + "pr.pregunta,\n"
                + "pr.idestatus,\n"
                + "pr.idcapitulo,\n"
                + "(select ca.capitulo from capitulo ca where ca.idcapitulo = pr.idcapitulo) capitulo,\n"
                + "pr.idsubcapitulo,\n"
                + "(select sc.subcapitulo from subcapitulo sc where sc.idsubcapitulo = pr.idsubcapitulo) subcapitulo,\n"
                + "pr.ordenmostrar,\n"
                + "pr.inciso,\n"
                + "pr.opcion,\n"
                + "pr.instruccionesllenado,\n"
                + "pr.opcionmultiple,\n"
                + "pr.encatalogo,\n"
                + "pr.catalogo,\n"
                + "pr.clavecatalogo,\n"
                + "pr.especificarxcatalogo,\n"
                + "pr.tipodedatoxcatalogo,\n"
                + "pr.longitudmaximaxcatalogo,\n"
                + "pr.decimaleslongmaxcat,\n"
                + "pr.subirimagen,\n"
                + "pr.otroespecificar,\n"
                + "pr.tipodedatootro,\n"
                + "pr.longitudmaximaotro,\n"
                + "pr.decimaleslongmaxotro,\n"
                + "pr.idcuestionarioref,\n"
                + "pr.idpreguntaref,\n"
                + "pr.nousuarioregistro,\n"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = pr.nousuarioregistro) nomusuarioregistro,\n"
                + "pr.fecharegistro,\n"
                + "pr.nousuariomodif,\n"
                + "(select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = pr.nousuariomodif) nomusuariomodif,\n"
                + "pr.fechamodif from pregunta pr WHERE pr.idpregunta =" + id;
        try {
            pr = jdbcTemplate.queryForObject(sql, new PreguntaRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return pr;
    }

    @Override
    public int deletePregunta(int id, int opcion) {
        int valor = 0;
        sql = "UPDATE pregunta SET IDESTATUS= ? where idpregunta = ?";
        try {
            valor = jdbcTemplate.update(sql, opcion, id);
        } catch (DataAccessException e) {
            System.err.print(e);
        }
        return valor;
    }

    @Override
    public List<Pregunta> getRegistrosPreguntas(int idCuestionario) {
        sql = "select pr.idcuestionario,\n"
                + "                (select cu.cuestionario from cuestionario cu where cu.idcuestionario = pr.idcuestionario) cuestionario,\n"
                + "                pr.idpregunta,\n"
                + "                pr.pregunta,\n"
                + "                pr.idestatus,\n"
                + "                pr.idcapitulo,\n"
                + "                (select ca.capitulo from capitulo ca where ca.idcapitulo = pr.idcapitulo) capitulo,\n"
                + "                pr.idsubcapitulo,\n"
                + "                (select sc.subcapitulo from subcapitulo sc where sc.idsubcapitulo = pr.idsubcapitulo) subcapitulo,\n"
                + "                pr.ordenmostrar,\n"
                + "                pr.inciso,\n"
                + "                pr.opcion,\n"
                + "                pr.instruccionesllenado,\n"
                + "                pr.opcionmultiple,\n"
                + "                pr.encatalogo,\n"
                + "                pr.catalogo,\n"
                + "                pr.clavecatalogo,\n"
                + "                pr.especificarxcatalogo,\n"
                + "                pr.tipodedatoxcatalogo,\n"
                + "                pr.longitudmaximaxcatalogo,\n"
                + "                pr.decimaleslongmaxcat,\n"
                + "                pr.subirimagen,\n"
                + "                pr.otroespecificar,\n"
                + "                pr.tipodedatootro,\n"
                + "                pr.longitudmaximaotro,\n"
                + "                pr.decimaleslongmaxotro,\n"
                + "                pr.idcuestionarioref,\n"
                + "                pr.idpreguntaref,\n"
                + "                pr.nousuarioregistro,\n"
                + "                (select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = pr.nousuarioregistro) nomusuarioregistro,\n"
                + "                pr.fecharegistro,\n"
                + "                pr.nousuariomodif,\n"
                + "                (select us.nombre||' '||us.apellido_paterno||' '||us.apellido_materno from usuarios us where us.no_usuario = pr.nousuariomodif) nomusuariomodif,\n"
                + "                pr.fechamodif from pregunta pr where pr.idcuestionario=? and pr.idestatus=1 \n"
                + "               order by idcapitulo, ordenmostrar";
        try {
            lista = jdbcTemplate.query(sql, new Object[]{idCuestionario}, new PreguntaRowMapper());
        } catch (DataAccessException e) {
            System.err.print(e);
        }

        return lista;
    }
}

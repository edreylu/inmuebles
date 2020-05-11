/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.pregunta;

import com.app.inmuebles.capitulo.Capitulo;
import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.subCapitulo.SubCapitulo;
import com.app.inmuebles.usuario.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author usuario
 */
public class PreguntaRowMapper implements RowMapper<Pregunta> {

    @Override
    public Pregunta mapRow(ResultSet rs, int rowNum) throws SQLException {
        Pregunta pr = new Pregunta();

        Cuestionario cuestionario = new Cuestionario();
        cuestionario.setIdCuestionario(rs.getInt("idcuestionario"));
        cuestionario.setCuestionario(rs.getString("cuestionario"));

        Usuario usuarioRegistro = new Usuario();
        usuarioRegistro.setNoUsuario(rs.getInt("nousuarioregistro"));
        usuarioRegistro.setNomUsuario(rs.getString("nomUsuarioRegistro"));

        Usuario usuarioModif = new Usuario();
        usuarioModif.setNoUsuario(rs.getInt("nousuariomodif"));
        usuarioModif.setNomUsuario(rs.getString("nomUsuarioModif"));

        Capitulo capitulo = new Capitulo();
        capitulo.setIdCapitulo(rs.getInt("idcapitulo"));
        capitulo.setCapitulo(rs.getString("capitulo"));

        SubCapitulo subcapitulo = new SubCapitulo();
        subcapitulo.setIdSubCapitulo(rs.getInt("idsubcapitulo"));
        subcapitulo.setSubCapitulo(rs.getString("subcapitulo"));

        pr.setCuestionario(cuestionario);
        pr.setIdPregunta(rs.getInt("idpregunta"));
        pr.setPregunta(rs.getString("pregunta"));
        pr.setIdEstatus(rs.getInt("idestatus"));
        pr.setCapitulo(capitulo);
        pr.setSubCapitulo(subcapitulo);
        pr.setOrdenMostrar(rs.getInt("ordenmostrar"));
        pr.setInciso(rs.getString("inciso"));
        pr.setOpcion(rs.getString("opcion"));
        pr.setInstruccionesLlenado(rs.getString("instruccionesllenado"));
        pr.setOpcionMultiple(rs.getString("opcionmultiple"));
        pr.setEnCatalogo(rs.getString("encatalogo"));
        pr.setCatalogo(rs.getString("catalogo"));
        pr.setClaveCatalogo(rs.getInt("clavecatalogo"));
        pr.setEspecificarxCatalogo(rs.getString("especificarxcatalogo"));
        pr.setTipoDeDatoxCatalogo(rs.getString("tipodedatoxcatalogo"));
        pr.setLongitudMaximaxCatalogo(rs.getInt("longitudmaximaxcatalogo"));
        pr.setDecimalesLongMaxCat(rs.getInt("decimaleslongmaxcat"));
        pr.setSubirImagen(rs.getString("subirimagen"));
        pr.setOtroEspecificar(rs.getString("otroespecificar"));
        pr.setTipoDeDatoOtro(rs.getString("tipodedatootro"));
        pr.setLongitudMaximaOtro(rs.getInt("longitudmaximaotro"));
        pr.setDecimalesLongMaxOtro(rs.getInt("decimaleslongmaxotro"));
        pr.setIdCuestionarioRef(rs.getInt("idcuestionarioref"));
        pr.setIdPreguntaRef(rs.getInt("idpreguntaref"));
        pr.setUsuarioRegistro(usuarioRegistro);
        pr.setFechaRegistro(rs.getDate("fecharegistro"));
        pr.setUsuarioModif(usuarioModif);
        pr.setFechaModif(rs.getDate("fechamodif"));
        pr.setEspecificarPor("N");
        if (Objects.equals(pr.getEspecificarxCatalogo(), "S"))
            pr.setEspecificarPor("C");

        else if (Objects.equals(pr.getOtroEspecificar(), "S"))
            pr.setEspecificarPor("O");
        
        return pr;

    }

}

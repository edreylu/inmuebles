/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.capitulo;

import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.usuario.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author usuario
 */
public class CapituloRowMapper implements RowMapper<Capitulo> {

    @Override
    public Capitulo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Capitulo ca = new Capitulo();

        Usuario usuarioRegistro = new Usuario();
        usuarioRegistro.setNoUsuario(rs.getInt("nousuarioregistro"));
        usuarioRegistro.setNomUsuario(rs.getString("nomUsuarioRegistro"));

        Usuario usuarioModif = new Usuario();
        usuarioModif.setNoUsuario(rs.getInt("nousuariomodif"));
        usuarioModif.setNomUsuario(rs.getString("nomUsuarioModif"));

        Cuestionario cuestionario = new Cuestionario();
        cuestionario.setIdCuestionario(rs.getInt("idcuestionario"));
        cuestionario.setCuestionario(rs.getString("cuestionario"));

        ca.setCuestionario(cuestionario);
        ca.setIdCapitulo(rs.getInt("idcapitulo"));
        ca.setCapitulo(rs.getString("capitulo"));
        ca.setFechaRegistro(rs.getDate("fecharegistro"));
        ca.setUsuarioRegistro(usuarioRegistro);
        ca.setFechaModif(rs.getString("fechamodif"));
        ca.setUsuarioModif(usuarioModif);
        ca.setIdEstatus(rs.getInt("idestatus"));
        return ca;

    }

}

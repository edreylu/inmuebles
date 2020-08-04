/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.cuestionario;

import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.usuario.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author usuario
 */
public class CuestionarioRowMapper implements RowMapper<Cuestionario> {

    @Override
    public Cuestionario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cuestionario cu = new Cuestionario();

        Usuario usuarioRegistro = new Usuario();
        usuarioRegistro.setNoUsuario(rs.getInt("nousuarioregistro"));
        usuarioRegistro.setNomUsuario(rs.getString("nomUsuarioRegistro"));

        Usuario usuarioModif = new Usuario();
        usuarioModif.setNoUsuario(rs.getInt("nousuariomodif"));
        usuarioModif.setNomUsuario(rs.getString("nomUsuarioModif"));

        cu.setIdCuestionario(rs.getInt("idcuestionario"));
        cu.setCuestionario(rs.getString("cuestionario"));
        cu.setFechaRegistro(rs.getDate("fecharegistro"));
        cu.setUsuarioRegistro(usuarioRegistro);
        cu.setFechaModif(rs.getString("fechamodif"));
        cu.setUsuarioModif(usuarioModif);
        cu.setIdEstatus(rs.getInt("idestatus"));
        return cu;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.roles;

import com.app.riife.roles.Roles;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class RolesRowMapper implements RowMapper<Roles> {

    @Override
    public Roles mapRow(ResultSet rs, int rowNum) throws SQLException {
        Roles rol = new Roles();
        rol.setNoRol(rs.getInt("NO_ROL"));
        rol.setDescripcion(rs.getString("DESCRIPCION"));
        rol.setInsertar(rs.getString("INSERTAR"));
        rol.setActualizar(rs.getString("ACTUALIZAR"));
        rol.setEliminar(rs.getString("ELIMINAR"));
        rol.setConsultar(rs.getString("CONSULTAR"));
        rol.setDescargar(rs.getString("DESCARGAR"));
        rol.setInsertarSel(Boolean.FALSE);
        rol.setActualizarSel(Boolean.FALSE);
        rol.setEliminarSel(Boolean.FALSE);
        rol.setConsultarSel(Boolean.FALSE);
        rol.setDescargarSel(Boolean.FALSE);
        if (rs.getString("INSERTAR").equals("S")) {
            rol.setInsertarSel(Boolean.TRUE);
        }
        if (rs.getString("ACTUALIZAR").equals("S")) {
            rol.setActualizarSel(Boolean.TRUE);
        }
        if (rs.getString("ELIMINAR").equals("S")) {
            rol.setEliminarSel(Boolean.TRUE);
        }
        if (rs.getString("CONSULTAR").equals("S")) {
            rol.setConsultarSel(Boolean.TRUE);
        }
        if (rs.getString("DESCARGAR").equals("S")) {
            rol.setDescargarSel(Boolean.TRUE);
        }

        return rol;

    }

}

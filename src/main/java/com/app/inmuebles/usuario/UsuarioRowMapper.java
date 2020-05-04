/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.usuario;

import com.app.inmuebles.roles.Roles;
import com.app.inmuebles.usuario.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author usuario
 */
public class UsuarioRowMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Usuario us = new Usuario();

        Roles rol = new Roles();
        rol.setNoRol(rs.getInt("rol"));
        String desc = "";
        if (rs.getString("nombreRol") != (null)) {
            desc = rs.getString("nombreRol");
        } else {
            desc = "SIN ROL";
        }
        rol.setDescripcion(desc);

        us.setNoUsuario(rs.getInt("no_usuario"));
        us.setClave(rs.getString("clave"));
        us.setNombre(rs.getString("nombre"));
        us.setApellidoPaterno(rs.getString("apellido_paterno"));
        us.setApellidoMaterno(rs.getString("apellido_materno"));
        us.setNomUsuario(rs.getString("nombre") + " " + rs.getString("apellido_paterno") + " " + rs.getString("apellido_materno"));
        us.setCorreo(rs.getString("correo"));
        us.setTelefono(rs.getString("telefono"));
        us.setTelefono2(rs.getString("telefono2"));
        us.setRol(rol);
        us.setIdEstatus(rs.getInt("idestatus"));

        return us;

    }

}

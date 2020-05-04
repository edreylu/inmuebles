/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.subCapitulo;

import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.subCapitulo.SubCapitulo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author usuario
 */
public class SubCapituloRowMapper implements RowMapper<SubCapitulo> {

    @Override
    public SubCapitulo mapRow(ResultSet rs, int rowNum) throws SQLException {
        SubCapitulo sc = new SubCapitulo();

        Cuestionario cuestionario = new Cuestionario();
        cuestionario.setIdCuestionario(rs.getInt("idcuestionario"));
        cuestionario.setCuestionario(rs.getString("cuestionario"));

        sc.setCuestionario(cuestionario);
        sc.setIdSubCapitulo(rs.getInt("idsubcapitulo"));
        sc.setSubCapitulo(rs.getString("subcapitulo"));
        sc.setIdEstatus(rs.getInt("idestatus"));
        return sc;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.kcatalogo;

import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.kcatalogo.Kcatalogo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author usuario
 */
public class KcatalogoRowMapper implements RowMapper<Kcatalogo> {

    @Override
    public Kcatalogo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Kcatalogo kca = new Kcatalogo();

        Cuestionario cuestionario = new Cuestionario();
        cuestionario.setIdCuestionario(rs.getInt("idcuestionario"));
        cuestionario.setCuestionario(rs.getString("cuestionario"));

        kca.setCuestionario(cuestionario);
        kca.setClaveCatalogo(rs.getInt("clavecatalogo"));
        kca.setCatalogo(rs.getString("catalogo"));
        kca.setDescripCorta(rs.getString("descripcorta"));
        kca.setDescripcion(rs.getString("descripcion"));
        kca.setObservaciones(rs.getString("observaciones"));
        kca.setIdEstatus(rs.getInt("idestatus"));
        kca.setLlave(rs.getInt("idcuestionario") + "" + rs.getInt("clavecatalogo") + "" + rs.getString("catalogo"));
        return kca;

    }

}

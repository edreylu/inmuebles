/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.formasMenu;

import com.app.inmuebles.formasMenu.FormasMenu;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Admin
 */
public class FormasMenuRowMapper implements RowMapper<FormasMenu> {

    @Override
    public FormasMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
        FormasMenu menu = new FormasMenu();
        menu.setNoFormaMenu(rs.getInt("ID_MENU"));
        menu.setTitulo(rs.getString("DESCRIPCION"));
        menu.setUrl(rs.getString("ENLACE"));
        menu.setIcono(rs.getString("ICONO"));
        menu.setNombrePapa(rs.getString("PADRE"));
        menu.setNoFormaPadre(rs.getShort("ID_MENU_PADRE"));

        return menu;

    }

}

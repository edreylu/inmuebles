/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.roles;

import com.app.inmuebles.formasMenu.FormasMenu;
import com.app.inmuebles.roles.Roles;
import com.app.inmuebles.roles.RolesRowMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Edward Reyes
 */
public interface RolesDAO {
    
    List<Roles> getRegistros();

    int addRol(Roles rol);

    int editRol(Roles rol);

    Roles getRol(int id);

    int deleteRol(int id);

    void assignFormaMenu(int rol, int forma);

    void deleteFormaMenu(int rol);

    int deleteRolUsuario(int id);

    boolean existsRolUsuarios(int noRol);

    boolean existsRolFormas(int noRol);

    List<FormasMenu> getRegistrosFormas(int noRol);
}

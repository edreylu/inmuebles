/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.roles;

import com.app.inmuebles.formasMenu.FormasMenu;
import com.app.inmuebles.roles.Roles;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface RolesService {
    
    List<Roles> listAll();

    int addRole(Roles role);

    Roles getRole(int id);

    int editRole(Roles role);

    int deleteRole(int id);

    void assignFormaMenu(int rol, int forma);

    void deleteFormaMenu(int rol);

    int deleteRolUsuario(int id);

    boolean existsRolUsuarios(int noRol);

    boolean existsRolFormas(int noRol);

    List<FormasMenu> getRegistrosFormas(int noRol);
}

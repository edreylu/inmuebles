/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.roles;

import com.app.riife.formasMenu.FormasMenu;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface RolesDAO {
    
    List<Roles> getRecords();

    int addRol(Roles rol);

    int editRol(Roles rol);

    Roles getRol(int noRol);

    int deleteRol(int noRol);

    void assignFormaMenu(int noRol, int noForma);

    void deleteFormaMenu(int noRol);

    int deleteRolToUsuario(int noUsuario);

    boolean existsRolesUsuarios(int noRol);

    boolean existsRolesFormas(int noRol);

    List<FormasMenu> getRecordsFormas(int noRol);
}

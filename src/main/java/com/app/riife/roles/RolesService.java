/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.roles;

import com.app.riife.formasMenu.FormasMenu;
import com.app.riife.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface RolesService {
    
    List<Roles> listAll();

    Mensaje add(Roles role);

    Roles get(int noRol);

    Mensaje update(Roles role);

    Mensaje delete(int noRol);

    Mensaje assignFormaMenuToRol(RolFormas rolFormas);

    int deleteRolToUsuario(int noRol);

    List<FormasMenu> listFormasById(int noRol);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.formasMenu;

import com.app.riife.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface FormasMenuService {
    
    List<FormasMenu> listAll();

    List<FormasMenu> listAllFathers();

    Mensaje add(FormasMenu formasMenu);

    FormasMenu get(int id);

    Mensaje update(FormasMenu formasMenu);

    Mensaje delete(int id);

    List<String> getPermissionToPages(int noUsuario);

    List<FormasMenu> getMenu(int noUsuario);

    boolean existsChild(int clave);

    boolean existsRolFormas(int noForma);
}

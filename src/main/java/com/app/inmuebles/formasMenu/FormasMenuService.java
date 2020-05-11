/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.formasMenu;

import com.app.inmuebles.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface FormasMenuService {
    
    List<FormasMenu> listAll();

    List<FormasMenu> listAllFathers();

    Mensaje addFormasMenu(FormasMenu formasMenu);

    FormasMenu getFormasMenu(int id);

    Mensaje editFormasMenu(FormasMenu formasMenu);

    Mensaje deleteFormasMenu(int id);

    List<String> getPermissionToPages(int noUsuario);

    List<FormasMenu> getMenu(int noUsuario);

    boolean existsHijos(int clave);

    boolean existsRolFormas(int noForma);
}

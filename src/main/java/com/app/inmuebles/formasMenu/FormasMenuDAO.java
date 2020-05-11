/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.formasMenu;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface FormasMenuDAO {
    
    List<FormasMenu> getRecords();

    List<FormasMenu> getRecordsFather();

    int addFormaMenu(FormasMenu menu);

    int editFormaMenu(FormasMenu menu);

    FormasMenu getFormaMenu(int id);

    int deleteFormaMenu(int id);

    List<String> getPermissionToPages(int noUsuario);

    List<FormasMenu> getMenu(int noUsuario);

    boolean existsHijos(int clave);

    boolean existsRolFormas(int noForma);
}

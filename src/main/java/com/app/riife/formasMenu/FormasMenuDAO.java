/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.formasMenu;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface FormasMenuDAO {
    
    List<FormasMenu> getRecords();

    List<FormasMenu> getRecordsFather();

    int add(FormasMenu menu);

    int update(FormasMenu menu);

    FormasMenu get(int id);

    int delete(int id);

    List<String> getPermissionToPages(int noUsuario);

    List<FormasMenu> getMenu(int noUsuario);

    boolean existsChild(int clave);

    boolean existsRolFormas(int noForma);
}

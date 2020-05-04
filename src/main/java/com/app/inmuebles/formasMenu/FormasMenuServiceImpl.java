/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.formasMenu;

import com.app.inmuebles.formasMenu.FormasMenu;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class FormasMenuServiceImpl implements FormasMenuService{

    @Autowired
    private FormasMenuDAO formasMenuDAO;

    @Override
    public List<FormasMenu> listAll() {
        return formasMenuDAO.getRegistros();
    }

    @Override
    public List<FormasMenu> listAllFathers() {
        return formasMenuDAO.getRegistrosp();
    }

    @Override
    public int addFormasMenu(FormasMenu formasMenu) {
        return formasMenuDAO.addFormaMenu(formasMenu);
    }

    @Override
    public FormasMenu getFormasMenu(int id) {
        return formasMenuDAO.getFormaMenu(id);
    }

    @Override
    public int editFormasMenu(FormasMenu formasMenu) {
        return formasMenuDAO.editFormaMenu(formasMenu);
    }

    @Override
    public int deleteFormasMenu(int id) {
        return formasMenuDAO.deleteFormaMenu(id);
    }

    @Override
    public List<String> getPermisoPantalla(int noUsuario) {
        return formasMenuDAO.getPermisoPantalla(noUsuario);
    }

    @Override
    public List<FormasMenu> getMenu(int noUsuario) {
        return formasMenuDAO.getMenu(noUsuario);
    }

    @Override
    public boolean existsHijos(int clave) {
        return formasMenuDAO.existsHijos(clave);
    }

    @Override
    public boolean existsRolFormas(int noForma) {
        return formasMenuDAO.existsRolFormas(noForma);
    }

}

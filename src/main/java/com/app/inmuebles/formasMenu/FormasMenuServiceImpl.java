/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.formasMenu;

import com.app.inmuebles.util.Mensaje;
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
    private Mensaje msg;

    @Override
    public List<FormasMenu> listAll() {
        return formasMenuDAO.getRecords();
    }

    @Override
    public List<FormasMenu> listAllFathers() {
        return formasMenuDAO.getRecordsFather();
    }

    @Override
    public Mensaje addFormasMenu(FormasMenu formasMenu) {
        int valor =formasMenuDAO.addFormaMenu(formasMenu);
        if (valor >= 1) {
            msg = new Mensaje("Agregado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public FormasMenu getFormasMenu(int id) {
        return formasMenuDAO.getFormaMenu(id);
    }

    @Override
    public Mensaje editFormasMenu(FormasMenu formasMenu) {
        boolean existe = formasMenuDAO.existsHijos(formasMenu.getNoFormaMenu());
        if (existe) {
            if (formasMenu.getNoFormaPadre() != 0) {
                msg = new Mensaje("No se pudo editar", 2);
            } else {
                int valor = formasMenuDAO.editFormaMenu(formasMenu);
                if (valor >= 1) {
                    msg = new Mensaje("Editado correctamente", 1);
                } else {
                    msg = new Mensaje("No se pudo editar", 2);
                }
            }
        } else {
            int valor = formasMenuDAO.editFormaMenu(formasMenu);
            if (valor >= 1) {
                msg = new Mensaje("Editado correctamente", 1);
            } else {
                msg = new Mensaje("No se pudo editar", 2);
            }
        }
        return msg;
    }

    @Override
    public Mensaje deleteFormasMenu(int id) {
        boolean existe = formasMenuDAO.existsHijos(id);
        if (existe) {
            msg = new Mensaje("No se pudo eliminar por que tiene pantallas asociadas", 2);
        } else {
            boolean existe2 = formasMenuDAO.existsRolFormas(id);
            if (existe2) {
                msg = new Mensaje("No se pudo eliminar por que la pantalla esta asignada a un Rol", 2);
            } else {
                int valor = formasMenuDAO.deleteFormaMenu(id);
                if (valor >= 1) {
                    msg = new Mensaje("Eliminado correctamente", 1);
                } else {
                    msg = new Mensaje("No se pudo eliminar", 2);
                }
            }
        }
        return msg;
    }

    @Override
    public List<String> getPermissionToPages(int noUsuario) {
        return formasMenuDAO.getPermissionToPages(noUsuario);
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

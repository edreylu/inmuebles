/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.formasMenu;

import com.app.riife.util.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class FormasMenuServiceImpl implements FormasMenuService{

    
    private final FormasMenuDAO formasMenuDAO;
    private Mensaje msg;

    @Autowired
    public FormasMenuServiceImpl(FormasMenuDAO formasMenuDAO) {
        this.formasMenuDAO = formasMenuDAO;
    }
    
    @Override
    public List<FormasMenu> listAll() {
        return formasMenuDAO.getRecords();
    }

    @Override
    public List<FormasMenu> listAllFathers() {
        return formasMenuDAO.getRecordsFather();
    }

    @Override
    public Mensaje add(FormasMenu formasMenu) {
        int valor =formasMenuDAO.add(formasMenu);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Agregado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public FormasMenu get(int id) {
        return formasMenuDAO.get(id);
    }

    @Override
    public Mensaje update(FormasMenu formasMenu) {
        boolean existe = formasMenuDAO.existsChild(formasMenu.getNoFormaMenu());
        if (existe) {
            if (formasMenu.getNoFormaPadre() != 0) {
                msg = Mensaje.CREATE("No se pudo editar", 2);
            } else {
                int valor = formasMenuDAO.update(formasMenu);
                if (valor >= 1) {
                    msg = Mensaje.CREATE("Editado correctamente", 1);
                } else {
                    msg = Mensaje.CREATE("No se pudo editar", 2);
                }
            }
        } else {
            int valor = formasMenuDAO.update(formasMenu);
            if (valor >= 1) {
                msg = Mensaje.CREATE("Editado correctamente", 1);
            } else {
                msg = Mensaje.CREATE("No se pudo editar", 2);
            }
        }
        return msg;
    }

    @Override
    public Mensaje delete(int id) {
        boolean existe = formasMenuDAO.existsChild(id);
        if (existe) {
            msg = Mensaje.CREATE("No se pudo eliminar por que tiene pantallas asociadas", 2);
        } else {
            boolean existe2 = formasMenuDAO.existsRolFormas(id);
            if (existe2) {
                msg = Mensaje.CREATE("No se pudo eliminar por que la pantalla esta asignada a un Rol", 2);
            } else {
                int valor = formasMenuDAO.delete(id);
                if (valor >= 1) {
                    msg = Mensaje.CREATE("Eliminado correctamente", 1);
                } else {
                    msg = Mensaje.CREATE("No se pudo eliminar", 2);
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
    public boolean existsChild(int clave) {
        return formasMenuDAO.existsChild(clave);
    }

    @Override
    public boolean existsRolFormas(int noForma) {
        return formasMenuDAO.existsRolFormas(noForma);
    }

}

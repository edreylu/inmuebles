/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.roles;

import com.app.riife.formasMenu.FormasMenu;
import com.app.riife.util.Mensaje;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class RolesServiceImpl implements RolesService{

    
    private final RolesDAO rolesDAO;
    private Mensaje msg;

    @Autowired
    public RolesServiceImpl(RolesDAO rolesDAO) {
        this.rolesDAO = rolesDAO;
    }

    @Override
    public List<Roles> listAll() {
        return rolesDAO.getRecords();
    }

    @Override
    public Mensaje add(Roles role) {
        int valor = rolesDAO.addRol(role);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Agregado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public Roles get(int id) {
        return rolesDAO.getRol(id);
    }

    @Override
    public Mensaje update(Roles role) {
        int valor = rolesDAO.updateRol(role);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Editado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo editar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje delete(int noRol) {
        boolean rolUsuarios = rolesDAO.existsRolesUsuarios(noRol);
        boolean rolFormas = rolesDAO.existsRolesFormas(noRol);
        if (rolUsuarios) {
            msg = Mensaje.CREATE("No se pudo eliminar rol por que esta en uso", 2);
        } else if (rolFormas) {
            msg = Mensaje.CREATE("No se pudo eliminar rol por que tiene formas asignadas", 2);
        } else {
            int valor = rolesDAO.deleteRol(noRol);
            if (valor >= 1) {
                msg = Mensaje.CREATE("Eliminado correctamente", 1);
            } else {
                msg = Mensaje.CREATE("No se pudo eliminar", 2);
            }
        }
        return msg;
    }

    @Override
    public Mensaje assignFormaMenuToRol(RolFormas rolFormas) {
        boolean existe = rolesDAO.existsRolesFormas(rolFormas.getNoRol());
        if (existe) {
            rolesDAO.deleteFormaMenuToRol(rolFormas.getNoRol());
        }
        for (Iterator it = rolFormas.getFormas().iterator(); it.hasNext();) {
            FormasMenu x = (FormasMenu) it.next();
            if (x.isMenuSeleccionado()) {
                rolesDAO.assignFormaMenuToRol(rolFormas.getNoRol(), x.getNoFormaMenu());
            }
            if (!it.hasNext()) {
                msg = Mensaje.CREATE("Pantallas asignadas correctamente", 1);
            }
        }
        return msg;
    }


    @Override
    public int deleteRolToUsuario(int noRol) {
        return rolesDAO.deleteRolToUsuario(noRol);
    }

    @Override
    public List<FormasMenu> listFormasById(int noRol) {
        return rolesDAO.getRecordsFormasById(noRol);
    }
}

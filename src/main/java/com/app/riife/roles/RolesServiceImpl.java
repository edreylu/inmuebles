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
    public Mensaje addRole(Roles role) {
        int valor = rolesDAO.addRol(role);
        if (valor >= 1) {
            msg = new Mensaje("Agregado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public Roles getRole(int id) {
        return rolesDAO.getRol(id);
    }

    @Override
    public Mensaje editRole(Roles role) {
        int valor = rolesDAO.editRol(role);
        if (valor >= 1) {
            msg = new Mensaje("Editado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo editar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje deleteRole(int noRol) {
        boolean rolUsuarios = rolesDAO.existsRolesUsuarios(noRol);
        boolean rolFormas = rolesDAO.existsRolesFormas(noRol);
        if (rolUsuarios) {
            msg = new Mensaje("No se pudo eliminar rol por que esta en uso", 2);
        } else if (rolFormas) {
            msg = new Mensaje("No se pudo eliminar rol por que tiene formas asignadas", 2);
        } else {
            int valor = rolesDAO.deleteRol(noRol);
            if (valor >= 1) {
                msg = new Mensaje("Eliminado correctamente", 1);
            } else {
                msg = new Mensaje("No se pudo eliminar", 2);
            }
        }
        return msg;
    }

    @Override
    public Mensaje assignFormaMenu(RolFormas rolFormas) {
        boolean existe = rolesDAO.existsRolesFormas(rolFormas.getNoRol());
        if (existe) {
            rolesDAO.deleteFormaMenu(rolFormas.getNoRol());
        }
        for (Iterator it = rolFormas.getFormas().iterator(); it.hasNext();) {
            FormasMenu x = (FormasMenu) it.next();
            if (x.isMenuSeleccionado()) {
                rolesDAO.assignFormaMenu(rolFormas.getNoRol(), x.getNoFormaMenu());
            }
            if (!it.hasNext()) {
                msg = new Mensaje("Pantallas asignadas correctamente", 1);
            }
        }
        return msg;
    }


    @Override
    public int deleteRolUsuario(int noRol) {
        return rolesDAO.deleteRolToUsuario(noRol);
    }

    @Override
    public List<FormasMenu> listFormasById(int noRol) {
        return rolesDAO.getRecordsFormas(noRol);
    }
}

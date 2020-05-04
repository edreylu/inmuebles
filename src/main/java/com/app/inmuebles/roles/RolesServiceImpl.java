/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.roles;

import com.app.inmuebles.formasMenu.FormasMenu;
import com.app.inmuebles.roles.Roles;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class RolesServiceImpl implements RolesService{

    @Autowired
    private RolesDAO rolesDAO;

    @Override
    public List<Roles> listAll() {
        return rolesDAO.getRegistros();
    }

    @Override
    public int addRole(Roles role) {
        return rolesDAO.addRol(role);
    }

    @Override
    public Roles getRole(int id) {
        return rolesDAO.getRol(id);
    }

    @Override
    public int editRole(Roles role) {
        return rolesDAO.editRol(role);
    }

    @Override
    public int deleteRole(int id) {
        return rolesDAO.deleteRol(id);
    }

    @Override
    public void assignFormaMenu(int rol, int forma) {
        rolesDAO.assignFormaMenu(rol, forma);
    }

    @Override
    public void deleteFormaMenu(int rol) {
        rolesDAO.deleteFormaMenu(rol);
    }

    @Override
    public int deleteRolUsuario(int id) {
        return rolesDAO.deleteRolUsuario(id);
    }

    @Override
    public boolean existsRolUsuarios(int noRol) {
        return rolesDAO.existsRolUsuarios(noRol);
    }

    @Override
    public boolean existsRolFormas(int noRol) {
        return rolesDAO.existsRolFormas(noRol);
    }

    @Override
    public List<FormasMenu> getRegistrosFormas(int noRol) {
        return rolesDAO.getRegistrosFormas(noRol);
    }
}

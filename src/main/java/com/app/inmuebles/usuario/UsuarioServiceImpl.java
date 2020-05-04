/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.usuario;

import com.app.inmuebles.usuario.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public List<Usuario> listAll() {
        return usuarioDAO.getRegistros();
    }

    @Override
    public int addUsuario(Usuario usuario) {
        return usuarioDAO.addUsuario(usuario);
    }

    @Override
    public Usuario getUsuario(int id) {
        return usuarioDAO.getUsuario(id);
    }

    @Override
    public int editUsuario(Usuario usuario) {
        return usuarioDAO.editUsuario(usuario);
    }

    @Override
    public int deleteUsuario(int id, int opcion) {
        return usuarioDAO.deleteUsuario(id, opcion);
    }

    @Override
    public Usuario existsUsuario(String clave, String pass) {
        return usuarioDAO.existsUsuario(clave, pass);
    }

    @Override
    public int assignRolUsuario(int usuario, int rol) {
        return usuarioDAO.assignRolUsuario(usuario, rol);
    }

    @Override
    public int deleteRolUsuario(int id) {
        return usuarioDAO.deleteRolUsuario(id);
    }

    @Override
    public int resetPasaporte(int us) {
        return usuarioDAO.resetPasaporte(us);
    }

    @Override
    public int changePasaporte(int us, String contra) {
        return usuarioDAO.changePasaporte(us, contra);
    }

    @Override
    public boolean existsRolUsuario(int noUsuario) {
        return usuarioDAO.existsRolUsuario(noUsuario);
    }

    @Override
    public boolean existsRolUsuarioAsignar(int noUsuario, int noRol) {
        return usuarioDAO.existsRolUsuarioAsignar(noUsuario, noRol);
    }
}

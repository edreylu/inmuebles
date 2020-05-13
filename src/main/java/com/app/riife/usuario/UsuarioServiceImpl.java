/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.usuario;

import com.app.riife.inicio.Login;
import com.app.riife.util.Mensaje;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioDAO usuarioDAO;
    private Mensaje msg;

    @Autowired
    public UsuarioServiceImpl(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    @Override
    public List<Usuario> listAll() {
        return usuarioDAO.getRecords();
    }

    @Override
    public Mensaje addUsuario(Usuario usuario) {
        int valor = usuarioDAO.addUsuario(usuario);
        if (valor >= 1) {
            usuarioDAO.deleteRolToUsuario(usuario.getNoUsuario());
            usuarioDAO.assignRolToUsuario(usuario);
            msg = new Mensaje("Agregado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public Usuario getUsuario(int id) {
        return usuarioDAO.getUsuario(id);
    }

    @Override
    public Mensaje editUsuario(Usuario usuario) {
        int valor = usuarioDAO.editUsuario(usuario);
        if (valor >= 1) {
            usuarioDAO.deleteRolToUsuario(usuario.getNoUsuario());
            usuarioDAO.assignRolToUsuario(usuario);
            msg = new Mensaje("Editado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo editar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje deleteUsuario(int id, int opcion) {
        int valor = usuarioDAO.deleteUsuario(id, opcion);
        if (valor >= 1) {
            boolean existe = usuarioDAO.existsRolAssignedToUsuario(id);
            if (existe) {
                usuarioDAO.deleteRolToUsuario(id);
            }
            msg = new Mensaje("Ejecutado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo ejecutar", 2);
        }
        return msg;
    }

    @Override
    public Usuario existsUsuario(Login login) {
        return usuarioDAO.existsUsuario(login);
    }

    @Override
    public Mensaje resetPasaporte(int id) {
        int valor = usuarioDAO.resetPasaporte(id);
        if (valor == 1) {
            msg = new Mensaje("Actualizado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo Actualizar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje changePasaporte(Login login) {
        boolean isValidUser;
        int valor = 0;
        if (Objects.equals(login.getContraseña(), login.getContraseña2())) {
            msg = new Mensaje("Contraseña actual y nueva no pueden ser iguales", 2);
            
        } else if (Objects.equals(login.getUsuario(), login.getContraseña2())) {
            msg = new Mensaje("Contraseña nueva y usuario no pueden ser iguales", 2);
        }
          else {
            Usuario usuario = usuarioDAO.existsUsuario(login);
            if (usuario.getNoUsuario() > 0) {
                valor = usuarioDAO.changePasaporte(usuario.getNoUsuario(), login.getContraseña2());
                isValidUser = valor > 0;
                if (isValidUser) {
                    msg = new Mensaje("Cambiado correctamente", 1);
                } else {
                    msg = new Mensaje("No se pudo Actualizar Password", 2);
                }
            } else {
                msg = new Mensaje("No existe Usuario", 2);
            }
        }
        return msg;
    }

}

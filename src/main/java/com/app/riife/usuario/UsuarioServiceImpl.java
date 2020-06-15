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
    public Mensaje add(Usuario usuario) {
        int valor = usuarioDAO.add(usuario);
        if (valor >= 1) {
            usuarioDAO.deleteRolToUsuario(usuario.getNoUsuario());
            usuarioDAO.assignRolToUsuario(usuario);
            msg = Mensaje.CREATE("Agregado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public Usuario get(int id) {
        return usuarioDAO.get(id);
    }

    @Override
    public Mensaje update(Usuario usuario) {
        int valor = usuarioDAO.update(usuario);
        if (valor >= 1) {
            usuarioDAO.deleteRolToUsuario(usuario.getNoUsuario());
            usuarioDAO.assignRolToUsuario(usuario);
            msg = Mensaje.CREATE("Editado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo editar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje delete(int id, int opcion) {
        int valor = usuarioDAO.delete(id, opcion);
        if (valor >= 1) {
            boolean existe = usuarioDAO.existsRolAssignedToUsuario(id);
            if (existe) {
                usuarioDAO.deleteRolToUsuario(id);
            }
            msg = Mensaje.CREATE("Ejecutado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo ejecutar", 2);
        }
        return msg;
    }

    @Override
    public Usuario exists(Login login) {
        return usuarioDAO.exists(login);
    }

    @Override
    public Mensaje resetPass(int id) {
        int valor = usuarioDAO.resetPass(id);
        if (valor == 1) {
            msg = Mensaje.CREATE("Actualizado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo Actualizar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje changePass(Login login) {
        boolean isValidUser;
        int valor = 0;
        if (Objects.equals(login.getContraseña(), login.getContraseña2())) {
            msg = Mensaje.CREATE("Contraseña actual y nueva no pueden ser iguales", 2);
            
        } else if (Objects.equals(login.getUsuario(), login.getContraseña2())) {
            msg = Mensaje.CREATE("Contraseña nueva y usuario no pueden ser iguales", 2);
        }
          else {
            Usuario usuario = usuarioDAO.exists(login);
            if (usuario.getNoUsuario() > 0) {
                valor = usuarioDAO.changePass(usuario.getNoUsuario(), login.getContraseña2());
                isValidUser = valor > 0;
                if (isValidUser) {
                    msg = Mensaje.CREATE("Cambiado correctamente", 1);
                } else {
                    msg = Mensaje.CREATE("No se pudo Actualizar Password", 2);
                }
            } else {
                msg = Mensaje.CREATE("No existe Usuario", 2);
            }
        }
        return msg;
    }

}

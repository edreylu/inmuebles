/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.usuario;

import com.app.riife.inicio.Login;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface UsuarioDAO {
     
    Usuario existsUsuario(Login login);

    List<Usuario> getRecords();

    int addUsuario(Usuario us);

    int editUsuario(Usuario us);

    Usuario getUsuario(int id);

    int deleteUsuario(int id, int opcion);

    int assignRolToUsuario(Usuario usuario);

    int deleteRolToUsuario(int id);

    int resetPasaporte(int us);

    int changePasaporte(int noUsuario, String contraseñaNueva);

    boolean existsRolAssignedToUsuario(int noUsuario);

}

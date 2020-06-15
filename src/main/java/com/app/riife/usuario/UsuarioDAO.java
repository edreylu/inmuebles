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
     
    Usuario exists(Login login);

    List<Usuario> getRecords();

    int add(Usuario us);

    int update(Usuario us);

    Usuario get(int id);

    int delete(int id, int opcion);

    int assignRolToUsuario(Usuario usuario);

    int deleteRolToUsuario(int id);

    int resetPass(int us);

    int changePass(int noUsuario, String contraseñaNueva);

    boolean existsRolAssignedToUsuario(int noUsuario);

}

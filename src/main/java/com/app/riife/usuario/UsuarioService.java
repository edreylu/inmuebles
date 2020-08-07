/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.usuario;

import com.app.riife.inicio.Login;
import com.app.riife.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface UsuarioService {

    List<Usuario> listAll();

    Mensaje add(Usuario usuario);

    Usuario get(int id);

    Mensaje update(Usuario usuario);

    Mensaje delete(int id, int opcion);

    Usuario exists(Login login);

    Mensaje resetPass(int id);

    Mensaje changePass(Login login);

}

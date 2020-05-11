/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.usuario;

import com.app.inmuebles.inicio.Login;
import com.app.inmuebles.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface UsuarioService {

    List<Usuario> listAll();

    Mensaje addUsuario(Usuario usuario);

    Usuario getUsuario(int id);

    Mensaje editUsuario(Usuario usuario);

    Mensaje deleteUsuario(int id, int opcion);

    Usuario existsUsuario(Login login);

    Mensaje resetPasaporte(int id);

    Mensaje changePasaporte(Login login);

}

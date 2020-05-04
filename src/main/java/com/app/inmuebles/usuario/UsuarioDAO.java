/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.usuario;

import com.app.inmuebles.usuario.Usuario;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface UsuarioDAO {
     
    Usuario existsUsuario(String clave, String pass);

    List<Usuario> getRegistros();

    int addUsuario(Usuario us);

    int editUsuario(Usuario us);

    Usuario getUsuario(int id);

    int deleteUsuario(int id, int opcion);

    int assignRolUsuario(int usuario, int rol);

    int deleteRolUsuario(int id);

    int resetPasaporte(int us);

    int changePasaporte(int us, String contra);

    boolean existsRolUsuario(int noUsuario);

    boolean existsRolUsuarioAsignar(int noUsuario, int noRol);
}

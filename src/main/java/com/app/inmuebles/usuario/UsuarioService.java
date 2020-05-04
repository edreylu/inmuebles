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
public interface UsuarioService {
    
    public List<Usuario> listAll();

    public int addUsuario(Usuario usuario);

    public Usuario getUsuario(int id);

    public int editUsuario(Usuario usuario);

    public int deleteUsuario(int id, int opcion);

    public Usuario existsUsuario(String clave, String pass);

    public int assignRolUsuario(int usuario, int rol);

    public int deleteRolUsuario(int id);

    public int resetPasaporte(int us);

    public int changePasaporte(int us, String contra);

    public boolean existsRolUsuario(int noUsuario);

    public boolean existsRolUsuarioAsignar(int noUsuario, int noRol);
}

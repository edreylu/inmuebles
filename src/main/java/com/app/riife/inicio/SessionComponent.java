/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.inicio;

import com.app.riife.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class SessionComponent {

    @Autowired
    private HttpSession session;
    private Usuario usuario;

    protected void setAttribute(String name,Object attr){
    session.setAttribute(name,attr);
    }
    protected void closeSession(){
    System.out.println("logout()");
        session.removeAttribute("usuario");
        session.removeAttribute("pantallas");
        session.removeAttribute("html");
        session.invalidate();
    }
    public Usuario getUsuario() {
        usuario = Objects.nonNull(session.getAttribute("usuario")) 
                ? (Usuario) session.getAttribute("usuario") 
                : new Usuario();
        return usuario;
    }
    
    public final int noUsuarioActivo(){
    return getUsuario().getNoUsuario();
    }

}

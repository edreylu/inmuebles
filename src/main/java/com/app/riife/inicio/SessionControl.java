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
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author Admin
 */
@Controller
@SessionScope
public class SessionControl {

    @Autowired
    private HttpSession session;
    private Usuario usuario;
    private boolean contiene;


    public String url(String url) {
        contiene = false;
        List<String> pantallas = (ArrayList<String>) session.getAttribute("pantallas");
        if (Objects.nonNull(session.getAttribute("usuario"))) {
            for (String pantalla : pantallas) {
                if (url.contains(pantalla)) {
                    contiene = true;
                }
            }
        }

        return contiene ? url : "redirect:/menu";
    }

    public Usuario getUsuario() {
        if (Objects.nonNull(session.getAttribute("usuario"))) {
            usuario = (Usuario) session.getAttribute("usuario");
        } else {
            usuario = new Usuario();
        }
        return usuario;
    }
    
    public final int noUsuarioActivo(){
    return getUsuario().getNoUsuario();
    }

}

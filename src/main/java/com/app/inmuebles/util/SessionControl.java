/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.util;

import com.app.inmuebles.usuario.Usuario;
import java.util.ArrayList;
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
    HttpSession session;
    Usuario usuario;
    String retorno;
    boolean contiene;

    public SessionControl() {
        retorno = "";
    }

    public String url(String url) {
        contiene = false;
        ArrayList<String> pantallas = (ArrayList<String>) session.getAttribute("pantallas");
        if (session.getAttribute("usuario") != null) {
            for (int i = 0; i < pantallas.size(); i++) {
                if (url.contains(pantallas.get(i))) {
                    contiene = true;
                }
            }
        }

        return retorno = !contiene ? "redirect:/menu" : url;
    }

    public Usuario getUsuario() {
        if (session.getAttribute("usuario") != null) {
            usuario = (Usuario) session.getAttribute("usuario");
        } else {
            usuario = new Usuario();
        }
        return usuario;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Admin
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mensaje {
    private String mensaje;
    private int result;

    public RedirectAttributes mensaje(String mensaje, String clase, RedirectAttributes redirectAttrs) {
        return redirectAttrs.addFlashAttribute("mensaje", mensaje).addFlashAttribute("clase", clase);
    }

    public RedirectAttributes crearMensaje(Mensaje msg, RedirectAttributes redirectAttrs) {
        String[] tipoMensajes = {"info", "success", "danger", "warning",
            "primary", "secundary", "light", "dark"};
        String clase = tipoMensajes[msg.getResult()];
        return mensaje(msg.getMensaje(), clase, redirectAttrs);
    }
    
    public static Mensaje CREATE(String mensaje, int result){
    return new Mensaje(mensaje,result);
    }
   
}

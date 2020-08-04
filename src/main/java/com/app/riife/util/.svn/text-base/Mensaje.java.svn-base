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

public RedirectAttributes success(String mensaje, RedirectAttributes redirectAttrs){
return redirectAttrs.addFlashAttribute("mensaje", mensaje).addFlashAttribute("clase", "success");
}
public RedirectAttributes danger(String mensaje, RedirectAttributes redirectAttrs){
return redirectAttrs.addFlashAttribute("mensaje", mensaje).addFlashAttribute("clase", "danger");
}

public RedirectAttributes crearMensaje(Mensaje msg, RedirectAttributes redirectAttrs){
return redirectAttrs.addFlashAttribute("mensaje", msg.getMensaje())
        .addFlashAttribute("clase", msg.getResult()==1 ? "success" : "danger");
}
   
}

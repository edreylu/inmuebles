/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.util;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Admin
 */

public class Mensaje {

public RedirectAttributes success(String mensaje, RedirectAttributes redirectAttrs){
return redirectAttrs.addFlashAttribute("mensaje", mensaje).addFlashAttribute("clase", "success");
}
public RedirectAttributes danger(String mensaje, RedirectAttributes redirectAttrs){
return redirectAttrs.addFlashAttribute("mensaje", mensaje).addFlashAttribute("clase", "danger");
}

   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.formasMenu;

import com.app.riife.inicio.SessionControl;
import com.app.riife.util.Mensaje;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author usuario
 */
@Controller
public class FormasMenuControl {

    @Autowired
    private SessionControl session;
    private final FormasMenuService formasMenuService;
    private List<FormasMenu> formas;
    private List<FormasMenu> formasPadre;
    private FormasMenu forma;
    private final Mensaje msg = new Mensaje();
    
    @Autowired
    public FormasMenuControl(FormasMenuService formasMenuService) {
        this.formasMenuService = formasMenuService;
    }

    @GetMapping("formas")
    public String listar(Model model) {
        formas = formasMenuService.listAll();
        model.addAttribute("lista", formas);
        return session.url("formas/principal");
    }

    @GetMapping("formas/agregar")
    public String agregar(Model model) {
        formasPadre = formasMenuService.listAllFathers();
        model.addAttribute("formasp", formasPadre);
        model.addAttribute(new FormasMenu());
        return session.url("formas/agregar");
    }

    @PostMapping(value = "formas/add")
    public String agregar(FormasMenu formas, RedirectAttributes redirectAttrs) {
        msg.crearMensaje(formasMenuService.addFormasMenu(formas), redirectAttrs);
        return "redirect:/formas";
    }

    @GetMapping(value = "formas/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        forma = formasMenuService.getFormasMenu(id);
        String validUrl = "redirect:/formas";
        if(Objects.nonNull(forma)){
        formasPadre = formasMenuService.listAllFathers();
        model.addAttribute("formasp", formasPadre);
        model.addAttribute("forma", forma);
        validUrl = "formas/editar";
        }
        return session.url(validUrl);
    }

    @PostMapping(value = "formas/update/{id}")
    public String editar(@PathVariable("id") int id, FormasMenu forma, RedirectAttributes redirectAttrs) {
        forma.setNoFormaMenu(id);
        msg.crearMensaje(formasMenuService.editFormasMenu(forma), redirectAttrs);
        return "redirect:/formas";
    }

    @GetMapping("formas/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        msg.crearMensaje(formasMenuService.deleteFormasMenu(id), redirectAttrs);
        return "redirect:/formas";
    }

}

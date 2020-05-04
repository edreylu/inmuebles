/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.formasMenu;

import com.app.inmuebles.util.SessionControl;
import com.app.inmuebles.formasMenu.FormasMenu;
import com.app.inmuebles.util.Mensaje;
import com.app.inmuebles.formasMenu.FormasMenuService;
import java.util.List;
import javax.validation.Valid;
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
    SessionControl session;
    @Autowired
    private FormasMenuService formasMenuService;
    List<FormasMenu> formas;
    List<FormasMenu> formasp;
    FormasMenu forma;
    List lista;
    int id;
    Mensaje msg = new Mensaje();

    @GetMapping("formas/principal")
    public String listar(Model model) {
        formas = formasMenuService.listAll();
        model.addAttribute("lista", formas);
        return session.url("formas/principal");
    }

    @GetMapping("formas/agregar")
    public String agregar(Model model) {
        formasp = formasMenuService.listAllFathers();
        model.addAttribute("formasp", formasp);
        model.addAttribute(new FormasMenu());
        return session.url("formas/agregar");
    }

    @PostMapping(value = "formas/add")
    public String agregar(FormasMenu formas, RedirectAttributes redirectAttrs) {
        int valor = formasMenuService.addFormasMenu(formas);
        if (valor >= 1) {
            msg.success("Agregado correctamente", redirectAttrs);
            System.out.println("se agrego registro: " + valor);
        } else {
            msg.danger("No se pudo agregar", redirectAttrs);
            System.err.println("no se agrego registro");
        }
        return "redirect:/formas/principal";
    }

    @GetMapping(value = "formas/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        forma = formasMenuService.getFormasMenu(id);
        formasp = formasMenuService.listAllFathers();
        model.addAttribute("formasp", formasp);
        model.addAttribute("forma", forma);

        return session.url("formas/editar");
    }

    @PostMapping(value = "formas/update/{id}")
    public String editar(@PathVariable("id") int id, @Valid FormasMenu forma, RedirectAttributes redirectAttrs) {
        boolean existe = false;
        forma.setNoFormaMenu(id);
        existe = formasMenuService.existsHijos(forma.getNoFormaMenu());
        if (existe) {
            if (forma.getNoFormaPadre() != 0) {
                msg.danger("No se pudo editar", redirectAttrs);
                System.err.println("no se edito registro 1");
            } else {
                forma.setNoFormaMenu(id);
                int valor = formasMenuService.editFormasMenu(forma);
                if (valor >= 1) {
                    msg.success("Editado correctamente", redirectAttrs);
                    System.out.println("se edito registro: " + valor);
                } else {
                    msg.danger("No se pudo editar", redirectAttrs);
                    System.err.println("no se edito registro");
                }
            }
        } else {
            forma.setNoFormaMenu(id);
            int valor = formasMenuService.editFormasMenu(forma);
            if (valor >= 1) {
                redirectAttrs.addFlashAttribute("mensaje", "Editado correctamente").addFlashAttribute("clase", "success");
                System.out.println("se edito registro: " + valor);
            } else {
                redirectAttrs.addFlashAttribute("mensaje", "No se pudo editar").addFlashAttribute("clase", "danger");
                System.err.println("no se edito registro");
            }
        }
        return "redirect:/formas/principal";
    }

    @GetMapping("formas/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        boolean existe = formasMenuService.existsHijos(id);
        if (existe) {
            msg.danger("No se pudo eliminar por que tiene pantallas asociadas", redirectAttrs);
            System.err.println("no se elimino registro");
        } else {
            boolean existe2 = formasMenuService.existsRolFormas(id);
            if (existe2) {
                msg.danger("No se pudo eliminar por que la pantalla esta asignada a un Rol", redirectAttrs);
                System.err.println("no se elimino registro");
            } else {
                int valor = formasMenuService.deleteFormasMenu(id);
                if (valor >= 1) {
                    msg.success("Eliminado correctamente", redirectAttrs);
                    System.out.println("se elimino registro: " + id);
                } else {
                    msg.danger("No se pudo eliminar", redirectAttrs);
                    System.err.println("no se elimino registro");
                }
            }
        }
        return "redirect:/formas/principal";
    }

}

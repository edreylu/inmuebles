/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.capitulo;

import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.util.Mensaje;
import com.app.inmuebles.util.SessionControl;
import com.app.inmuebles.cuestionario.CuestionarioService;
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
public class CapituloControl {

    @Autowired
    SessionControl session;
    @Autowired
    private CapituloService capituloService;
    @Autowired
    private CuestionarioService cuestionarioService;
    List<Capitulo> datos;
    List<Cuestionario> cuestionarios;
    Capitulo capitulo;
    List lista;
    int id;
    Mensaje msg = new Mensaje();

    @GetMapping("capitulos/principal")
    public String listar(Model model) {
        datos = capituloService.listAll();
        model.addAttribute("lista", datos);
        return session.url("capitulos/principal");
    }

    @GetMapping("capitulos/agregar")
    public String agregar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("cuestionarios", cuestionarios);
        model.addAttribute(new Capitulo());
        return session.url("capitulos/agregar");
    }

    @PostMapping(value = "capitulos/add")
    public String agregar(Capitulo ca, RedirectAttributes redirectAttrs) {
        ca.getUsuarioRegistro().setNoUsuario(session.getUsuario().getNoUsuario());
        int valor = capituloService.addCapitulo(ca);
        if (valor >= 1) {
            msg.success("Agregado correctamente", redirectAttrs);
            System.out.println("se agrego registro: " + valor);
        } else {
            msg.danger("No se pudo agregar", redirectAttrs);
            System.err.println("no se agrego registro");
        }
        return "redirect:/capitulos/principal";
    }

    @GetMapping(value = "capitulos/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        capitulo = capituloService.getCapitulo(id);
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("capitulo", capitulo);
        model.addAttribute("cuestionarios", cuestionarios);
        return session.url("capitulos/editar");
    }

    @PostMapping(value = "capitulos/update/{id}")
    public String editar(@PathVariable("id") int id, @Valid Capitulo ca, RedirectAttributes redirectAttrs) {
        ca.getUsuarioModif().setNoUsuario(session.getUsuario().getNoUsuario());
        ca.setIdCapitulo(id);
        int valor = capituloService.editCapitulo(ca);
        if (valor >= 1) {
            msg.success("Editado correctamente", redirectAttrs);
            System.out.println("se edito registro: " + valor);
        } else {
            msg.danger("No se pudo editar", redirectAttrs);
            System.err.println("no se edito registro");
        }
        return "redirect:/capitulos/principal";
    }

    @GetMapping("capitulos/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus,
            RedirectAttributes redirectAttrs) {

        int valor = capituloService.deleteCapitulo(id, idestatus);
        if (valor >= 1) {
            msg.success("Ejecutado correctamente", redirectAttrs);
            System.out.println("se elimino registro: " + id);
        } else {
            msg.danger("No se pudo ejecutar", redirectAttrs);
            System.err.println("no se elimino registro");
        }
        return "redirect:/capitulos/principal";
    }

}

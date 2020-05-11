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
import java.util.Objects;
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
    private SessionControl session;
    @Autowired
    private CapituloService capituloService;
    @Autowired
    private CuestionarioService cuestionarioService;
    private List<Capitulo> capitulos;
    private List<Cuestionario> cuestionarios;
    private Capitulo capitulo;
    private final Mensaje msg = new Mensaje();

    @GetMapping("capitulos/principal")
    public String listar(Model model) {
        capitulos = capituloService.listAll();
        model.addAttribute("lista", capitulos);
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
        ca.getUsuarioRegistro().setNoUsuario(session.noUsuarioActivo());
        msg.crearMensaje(capituloService.addCapitulo(ca), redirectAttrs);
        return "redirect:/capitulos/principal";
    }

    @GetMapping(value = "capitulos/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        capitulo = capituloService.getCapitulo(id);
        String validUrl = "redirect:/capitulos/principal";
        if(Objects.nonNull(capitulo)){
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("capitulo", capitulo);
        model.addAttribute("cuestionarios", cuestionarios);
        validUrl = "capitulos/editar";
        }
        return session.url(validUrl);
    }

    @PostMapping(value = "capitulos/update/{id}")
    public String editar(@PathVariable("id") int id, @Valid Capitulo ca, RedirectAttributes redirectAttrs) {
        ca.getUsuarioModif().setNoUsuario(session.getUsuario().getNoUsuario());
        ca.setIdCapitulo(id);
        msg.crearMensaje(capituloService.editCapitulo(ca), redirectAttrs);
        return "redirect:/capitulos/principal";
    }

    @GetMapping("capitulos/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus,
            RedirectAttributes redirectAttrs) {
        msg.crearMensaje(capituloService.deleteCapitulo(id, idestatus), redirectAttrs);
        return "redirect:/capitulos/principal";
    }

}

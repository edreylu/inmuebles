/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.capitulo;

import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.util.Mensaje;
import com.app.riife.cuestionario.CuestionarioService;
import com.app.riife.inicio.SessionComponent;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author usuario
 */
@Controller
@SessionScope
public class CapituloControl {
    
    private final SessionComponent session;
    private final CapituloService capituloService;
    private final CuestionarioService cuestionarioService;
    private List<Capitulo> capitulos;
    private List<Cuestionario> cuestionarios;
    private Capitulo capitulo;
    private final Mensaje msg = new Mensaje();
    
    @Autowired
    public CapituloControl(CapituloService capituloService, CuestionarioService cuestionarioService, 
            SessionComponent session) {
        this.capituloService = capituloService;
        this.cuestionarioService = cuestionarioService;
        this.session = session;
    }
    
    @GetMapping("capitulos")
    public String listar(Model model) {
        capitulos = capituloService.listAll();
        model.addAttribute("lista", capitulos);
        return "capitulos/principal";
    }

    @GetMapping("capitulos/agregar")
    public String agregar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("cuestionarios", cuestionarios);
        model.addAttribute(new Capitulo());
        return "capitulos/agregar";
    }

    @PostMapping(value = "capitulos/add")
    public String agregar(Capitulo ca, RedirectAttributes redirectAttrs) {
        ca.getUsuarioRegistro().setNoUsuario(session.noUsuarioActivo());
        msg.crearMensaje(capituloService.add(ca), redirectAttrs);
        return "redirect:/capitulos";
    }

    @GetMapping(value = "capitulos/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        capitulo = capituloService.get(id);
        String validUrl = "redirect:/capitulos/principal";
        if(Objects.nonNull(capitulo)){
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("capitulo", capitulo);
        model.addAttribute("cuestionarios", cuestionarios);
        validUrl = "capitulos/editar";
        }
        return validUrl;
    }

    @PostMapping(value = "capitulos/update/{id}")
    public String editar(@PathVariable("id") int id, Capitulo ca, RedirectAttributes redirectAttrs) {
        ca.getUsuarioModif().setNoUsuario(session.getUsuario().getNoUsuario());
        ca.setIdCapitulo(id);
        msg.crearMensaje(capituloService.update(ca), redirectAttrs);
        return "redirect:/capitulos";
    }

    @GetMapping("capitulos/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus,
            RedirectAttributes redirectAttrs) {
        msg.crearMensaje(capituloService.delete(id, idestatus), redirectAttrs);
        return "redirect:/capitulos";
    }

}

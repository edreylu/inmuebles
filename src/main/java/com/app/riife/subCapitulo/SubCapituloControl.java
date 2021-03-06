/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.subCapitulo;

import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.util.Mensaje;
import com.app.riife.cuestionario.CuestionarioService;
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
public class SubCapituloControl {

    private final SubCapituloService subCapituloService;
    private final CuestionarioService cuestionarioService;
    private List<SubCapitulo> subCapitulos;
    private List<Cuestionario> cuestionarios;
    private SubCapitulo subCapitulo;
    private final Mensaje msg = new Mensaje();

    @Autowired
    public SubCapituloControl(SubCapituloService subCapituloService, CuestionarioService cuestionarioService) {
        this.subCapituloService = subCapituloService;
        this.cuestionarioService = cuestionarioService;
    }

    @GetMapping("subcapitulos")
    public String listar(Model model) {
        subCapitulos = subCapituloService.listAll();
        model.addAttribute("lista", subCapitulos);
        return "subcapitulos/principal";
    }

    @GetMapping("subcapitulos/agregar")
    public String agregar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("cuestionarios", cuestionarios);
        model.addAttribute(new SubCapitulo());
        return "subcapitulos/agregar";
    }

    @PostMapping(value = "subcapitulos/add")
    public String agregar(SubCapitulo sc, RedirectAttributes redirectAttrs) {
        msg.crearMensaje(subCapituloService.add(sc), redirectAttrs);
        
        return "redirect:/subcapitulos";
    }

    @GetMapping(value = "subcapitulos/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        subCapitulo = subCapituloService.get(id);
        String validUrl = "redirect:/subcapitulos";
        if(Objects.nonNull(subCapitulo)){
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("subCapitulo", subCapitulo);
        model.addAttribute("cuestionarios", cuestionarios);
        validUrl="subcapitulos/editar";
        }
        return validUrl;
    }

    @PostMapping(value = "subcapitulos/update/{id}")
    public String editar(@PathVariable("id") int id, SubCapitulo sc, 
            RedirectAttributes redirectAttrs) {
        sc.setIdSubCapitulo(id);
        msg.crearMensaje(subCapituloService.update(sc), redirectAttrs);
        
        return "redirect:/subcapitulos";
    }

    @GetMapping("subcapitulos/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus, 
            RedirectAttributes redirectAttrs) {

        msg.crearMensaje(subCapituloService.delete(id, idestatus), redirectAttrs);
        return "redirect:/subcapitulos";
    }

}

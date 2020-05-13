/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.subCapitulo;

import com.app.riife.util.SessionControl;
import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.util.Mensaje;
import com.app.riife.cuestionario.CuestionarioService;
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
public class SubCapituloControl {

    @Autowired
    private SessionControl session;
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

    @GetMapping("subcapitulos/principal")
    public String listar(Model model) {
        subCapitulos = subCapituloService.listAll();
        model.addAttribute("lista", subCapitulos);
        return session.url("subcapitulos/principal");
    }

    @GetMapping("subcapitulos/agregar")
    public String agregar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("cuestionarios", cuestionarios);
        model.addAttribute(new SubCapitulo());
        return session.url("subcapitulos/agregar");
    }

    @PostMapping(value = "subcapitulos/add")
    public String agregar(SubCapitulo sc, RedirectAttributes redirectAttrs) {
        msg.crearMensaje(subCapituloService.addSubCapitulo(sc), redirectAttrs);
        
        return "redirect:/subcapitulos/principal";
    }

    @GetMapping(value = "subcapitulos/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        subCapitulo = subCapituloService.getSubCapitulo(id);
        String validUrl = "redirect:/subcapitulos/principal";
        if(Objects.nonNull(subCapitulo)){
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("subCapitulo", subCapitulo);
        model.addAttribute("cuestionarios", cuestionarios);
        validUrl="subcapitulos/editar";
        }
        return session.url(validUrl);
    }

    @PostMapping(value = "subcapitulos/update/{id}")
    public String editar(@PathVariable("id") int id, @Valid SubCapitulo sc, 
            RedirectAttributes redirectAttrs) {
        sc.setIdSubCapitulo(id);
        msg.crearMensaje(subCapituloService.editSubCapitulo(sc), redirectAttrs);
        
        return "redirect:/subcapitulos/principal";
    }

    @GetMapping("subcapitulos/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus, 
            RedirectAttributes redirectAttrs) {

        msg.crearMensaje(subCapituloService.deleteSubCapitulo(id, idestatus), redirectAttrs);
        return "redirect:/subcapitulos/principal";
    }

}

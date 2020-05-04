/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.subCapitulo;

import com.app.inmuebles.util.SessionControl;
import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.util.Mensaje;
import com.app.inmuebles.subCapitulo.SubCapitulo;
import com.app.inmuebles.cuestionario.CuestionarioService;
import com.app.inmuebles.subCapitulo.SubCapituloService;
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
public class SubCapituloControl {

    @Autowired
    SessionControl session;
    @Autowired
    private SubCapituloService subCapituloService;
    @Autowired
    private CuestionarioService cuestionarioService;
    List<SubCapitulo> datos;
    List<Cuestionario> cuestionarios;
    SubCapitulo subCapitulo;
    List lista;
    int id;
    Mensaje msg = new Mensaje();

    @GetMapping("subcapitulos/principal")
    public String listar(Model model) {
        datos = subCapituloService.listAll();
        model.addAttribute("lista", datos);
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
        int valor = subCapituloService.addSubCapitulo(sc);
        if (valor >= 1) {
            msg.success("Agregado correctamente", redirectAttrs);
            System.out.println("se agrego registro: " + valor);
        } else {
            msg.danger("No se pudo agregar", redirectAttrs);
            System.err.println("no se agrego registro");
        }
        return "redirect:/subcapitulos/principal";
    }

    @GetMapping(value = "subcapitulos/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        subCapitulo = subCapituloService.getSubCapitulo(id);
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("subCapitulo", subCapitulo);
        model.addAttribute("cuestionarios", cuestionarios);
        return session.url("subcapitulos/editar");
    }

    @PostMapping(value = "subcapitulos/update/{id}")
    public String editar(@PathVariable("id") int id, @Valid SubCapitulo sc, RedirectAttributes redirectAttrs) {
        sc.setIdSubCapitulo(id);
        int valor = subCapituloService.editSubCapitulo(sc);
        if (valor >= 1) {
            msg.success("Editado correctamente", redirectAttrs);
            System.out.println("se edito registro: " + valor);
        } else {
            msg.danger("No se pudo editar", redirectAttrs);
            System.err.println("no se edito registro");
        }
        return "redirect:/subcapitulos/principal";
    }

    @GetMapping("subcapitulos/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus, RedirectAttributes redirectAttrs) {

        int valor = subCapituloService.deleteSubCapitulo(id, idestatus);
        if (valor >= 1) {
            msg.success("Ejecutado correctamente", redirectAttrs);
            System.out.println("se elimino registro: " + id);
        } else {
            msg.danger("No se pudo ejecutar", redirectAttrs);
            System.err.println("no se elimino registro");
        }
        return "redirect:/subcapitulos/principal";
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.cuestionario;

import com.app.inmuebles.util.SessionControl;
import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.util.Mensaje;
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
public class CuestionarioControl {

    @Autowired
    SessionControl session;
    @Autowired
    private CuestionarioService cuestionarioService;
    List<Cuestionario> datos;
    Cuestionario cuestionario;
    List lista;
    int id;
    Mensaje msg = new Mensaje();

    @GetMapping("cuestionarios/principal")
    public String listar(Model model) {
        datos = cuestionarioService.listAll();
        model.addAttribute("lista", datos);
        return session.url("cuestionarios/principal");
    }

    @GetMapping("cuestionarios/agregar")
    public String agregar(Model model) {
        model.addAttribute(new Cuestionario());
        return session.url("cuestionarios/agregar");
    }

    @PostMapping(value = "cuestionarios/add")
    public String agregar(Cuestionario cu, RedirectAttributes redirectAttrs) {
        cu.getUsuarioRegistro().setNoUsuario(session.getUsuario().getNoUsuario());
        int valor = cuestionarioService.addCuestionario(cu);
        if (valor >= 1) {
            msg.success("Agregado correctamente", redirectAttrs);
            System.out.println("se agrego registro: " + valor);
        } else {
            msg.danger("No se pudo agregar", redirectAttrs);
            System.err.println("no se agrego registro");
        }
        return "redirect:/cuestionarios/principal";
    }

    @GetMapping(value = "cuestionarios/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        cuestionario = cuestionarioService.getCuestionario(id);
        model.addAttribute("cuestionario", cuestionario);
        return session.url("cuestionarios/editar");
    }

    @PostMapping(value = "cuestionarios/update/{id}")
    public String editar(@PathVariable("id") int id, @Valid Cuestionario cu, RedirectAttributes redirectAttrs) {
        cu.getUsuarioModif().setNoUsuario(session.getUsuario().getNoUsuario());
        cu.setIdCuestionario(id);
        int valor = cuestionarioService.editCuestionario(cu);
        if (valor >= 1) {
            msg.success("Editado correctamente", redirectAttrs);
            System.out.println("se edito registro: " + valor);
        } else {
            msg.danger("No se pudo editar", redirectAttrs);
            System.err.println("no se edito registro");
        }
        return "redirect:/cuestionarios/principal";
    }

    @GetMapping("cuestionarios/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus, RedirectAttributes redirectAttrs) {

        int valor = cuestionarioService.deleteCuestionario(id, idestatus);
        if (valor >= 1) {
            msg.success("Ejecutado correctamente", redirectAttrs);
            System.out.println("se elimino registro: " + id);
        } else {
            msg.danger("No se pudo ejecutar", redirectAttrs);
            System.err.println("no se elimino registro");
        }
        return "redirect:/cuestionarios/principal";
    }

}

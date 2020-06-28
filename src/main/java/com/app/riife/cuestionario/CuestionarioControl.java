/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.cuestionario;

import com.app.riife.inicio.SessionComponent;
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
public class CuestionarioControl {

    private final SessionComponent session;
    private final CuestionarioService cuestionarioService;
    private List<Cuestionario> cuestionarios;
    private Cuestionario cuestionario;
    private final Mensaje msg = new Mensaje();
    
    @Autowired
    public CuestionarioControl(SessionComponent session, CuestionarioService cuestionarioService) {
        this.session = session;
        this.cuestionarioService = cuestionarioService;
    }
    
    @GetMapping("cuestionarios")
    public String listar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("lista", cuestionarios);
        return "cuestionarios/principal";
    }

    @GetMapping("cuestionarios/agregar")
    public String agregar(Model model) {
        model.addAttribute(new Cuestionario());
        return "cuestionarios/agregar";
    }

    @PostMapping(value = "cuestionarios/add")
    public String agregar(Cuestionario cu, RedirectAttributes redirectAttrs) {
        cu.getUsuarioRegistro().setNoUsuario(session.noUsuarioActivo());
        msg.crearMensaje(cuestionarioService.add(cu), redirectAttrs);
        return "redirect:/cuestionarios";
    }

    @GetMapping(value = "cuestionarios/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        cuestionario = cuestionarioService.get(id);
        String validUrl = Objects.isNull(cuestionario) ? 
                "redirect:/cuestionarios" : "cuestionarios/editar";
        model.addAttribute("cuestionario", cuestionario);
        return validUrl;
        
    }

    @PostMapping(value = "cuestionarios/update/{id}")
    public String editar(@PathVariable("id") int id, Cuestionario cu, RedirectAttributes redirectAttrs) {
        cu.getUsuarioModif().setNoUsuario(session.noUsuarioActivo());
        cu.setIdCuestionario(id);
        msg.crearMensaje(cuestionarioService.update(cu), redirectAttrs);
        return "redirect:/cuestionarios";
    }

    @GetMapping("cuestionarios/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus, 
            RedirectAttributes redirectAttrs) {
        msg.crearMensaje(cuestionarioService.delete(id, idestatus), redirectAttrs);
        return "redirect:/cuestionarios";
    }

}

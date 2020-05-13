/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.kcatalogo;

import com.app.inmuebles.util.SessionControl;
import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.util.Mensaje;
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
public class KcatalogoControl {

    @Autowired
    private SessionControl session;
    private final KcatalogoService kcatalogoService;
    private final CuestionarioService cuestionarioService;
    private List<Kcatalogo> catalogos;
    private List<Cuestionario> cuestionarios;
    private Kcatalogo catalogo;
    private final Mensaje msg = new Mensaje();
    
    @Autowired
    public KcatalogoControl(KcatalogoService kcatalogoService, CuestionarioService cuestionarioService) {
        this.kcatalogoService = kcatalogoService;
        this.cuestionarioService = cuestionarioService;
    }

    @GetMapping("kcatalogos/principal")
    public String listar(Model model) {
        catalogos = kcatalogoService.listAll();
        model.addAttribute("lista", catalogos);
        return session.url("kcatalogos/principal");
    }

    @GetMapping("kcatalogos/agregar")
    public String agregar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("cuestionarios", cuestionarios);
        model.addAttribute(new Kcatalogo());
        return session.url("kcatalogos/agregar");
    }

    @PostMapping(value = "kcatalogos/add")
    public String agregar(Kcatalogo kca, RedirectAttributes redirectAttrs) {
        msg.crearMensaje(kcatalogoService.addKcatalogo(kca), redirectAttrs);
        return "redirect:/kcatalogos/principal";
    }

    @GetMapping(value = "kcatalogos/editar/{llave}")
    public String editar(@PathVariable("llave") String llave, Model model) {
        catalogo = kcatalogoService.getKcatalogo(llave);
        String validUrl = "redirect:/kcatalogos/principal";
        if(Objects.nonNull(catalogo)){
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("kcatalogo", catalogo);
        model.addAttribute("cuestionarios", cuestionarios);
        validUrl = "kcatalogos/editar";
        }
        return session.url(validUrl);
    }

    @PostMapping(value = "kcatalogos/update/{llave}")
    public String editar(@PathVariable("llave") String llave, @Valid Kcatalogo kca, RedirectAttributes redirectAttrs) {
        //kca.setClaveCatalogo(id);
        msg.crearMensaje(kcatalogoService.editKcatalogo(kca), redirectAttrs);
        return "redirect:/kcatalogos/principal";
    }

    @GetMapping("kcatalogos/eliminar/{idestatus}/{llave}")
    public String eliminar(@PathVariable("idestatus") int idestatus, @PathVariable("llave") String llave,
            RedirectAttributes redirectAttrs) {
        idestatus = idestatus == 2 ? 1 : 2;
        msg.crearMensaje(kcatalogoService.deleteKcatalogo(llave, idestatus), redirectAttrs);
        return "redirect:/kcatalogos/principal";
    }

}

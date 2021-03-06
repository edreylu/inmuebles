/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.kcatalogo;

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
public class KcatalogoControl {

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

    @GetMapping("kcatalogos")
    public String listar(Model model) {
        catalogos = kcatalogoService.listAll();
        model.addAttribute("lista", catalogos);
        return "kcatalogos/principal";
    }

    @GetMapping("kcatalogos/agregar")
    public String agregar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("cuestionarios", cuestionarios);
        model.addAttribute(new Kcatalogo());
        return "kcatalogos/agregar";
    }

    @PostMapping(value = "kcatalogos/add")
    public String agregar(Kcatalogo kca, RedirectAttributes redirectAttrs) {
        msg.crearMensaje(kcatalogoService.add(kca), redirectAttrs);
        return "redirect:/kcatalogos";
    }

    @GetMapping(value = "kcatalogos/editar/{llave}")
    public String editar(@PathVariable("llave") String llave, Model model) {
        catalogo = kcatalogoService.get(llave);
        String validUrl = "redirect:/kcatalogos";
        if(Objects.nonNull(catalogo)){
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("kcatalogo", catalogo);
        model.addAttribute("cuestionarios", cuestionarios);
        validUrl = "kcatalogos/editar";
        }
        return validUrl;
    }

    @PostMapping(value = "kcatalogos/update/{llave}")
    public String editar(@PathVariable("llave") String llave, Kcatalogo kca, RedirectAttributes redirectAttrs) {
        //kca.setClaveCatalogo(id);
        msg.crearMensaje(kcatalogoService.update(kca), redirectAttrs);
        return "redirect:/kcatalogos";
    }

    @GetMapping("kcatalogos/eliminar/{idestatus}/{llave}")
    public String eliminar(@PathVariable("idestatus") int idestatus, @PathVariable("llave") String llave,
            RedirectAttributes redirectAttrs) {
        idestatus = idestatus == 2 ? 1 : 2;
        msg.crearMensaje(kcatalogoService.delete(llave, idestatus), redirectAttrs);
        return "redirect:/kcatalogos";
    }

}

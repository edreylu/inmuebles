/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.kcatalogo;

import com.app.inmuebles.util.SessionControl;
import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.kcatalogo.Kcatalogo;
import com.app.inmuebles.util.Mensaje;
import com.app.inmuebles.cuestionario.CuestionarioService;
import com.app.inmuebles.kcatalogo.KcatalogoService;
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
public class KcatalogoControl {

    @Autowired
    SessionControl session;
    @Autowired
    private KcatalogoService kcatalogoService;
    @Autowired
    private CuestionarioService cuestionarioService;
    List<Kcatalogo> datos;
    List<Cuestionario> cuestionarios;
    Kcatalogo kcatalogo;
    List lista;
    Mensaje msg = new Mensaje();

    @GetMapping("kcatalogos/principal")
    public String listar(Model model) {
        datos = kcatalogoService.listAll();
        model.addAttribute("lista", datos);
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
        int valor = kcatalogoService.addKcatalogo(kca);
        if (valor >= 1) {
            msg.success("Agregado correctamente", redirectAttrs);
            System.out.println("se agrego registro: " + valor);
        } else {
            msg.danger("No se pudo agregar", redirectAttrs);
            System.err.println("no se agrego registro");
        }
        return "redirect:/kcatalogos/principal";
    }

    @GetMapping(value = "kcatalogos/editar/{llave}")
    public String editar(@PathVariable("llave") String llave, Model model) {
        kcatalogo = kcatalogoService.getKcatalogo(llave);
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("kcatalogo", kcatalogo);
        model.addAttribute("cuestionarios", cuestionarios);
        return session.url("kcatalogos/editar");
    }

    @PostMapping(value = "kcatalogos/update/{llave}")
    public String editar(@PathVariable("llave") String llave, @Valid Kcatalogo kca, RedirectAttributes redirectAttrs) {
        //kca.setClaveCatalogo(id);
        int valor = kcatalogoService.editKcatalogo(kca);
        if (valor >= 1) {
            msg.success("Editado correctamente", redirectAttrs);
            System.out.println("se edito registro: " + valor);
        } else {
            msg.danger("No se pudo editar", redirectAttrs);
            System.err.println("no se edito registro");
        }
        return "redirect:/kcatalogos/principal";
    }

    @GetMapping("kcatalogos/eliminar/{idestatus}/{llave}")
    public String eliminar(@PathVariable("idestatus") int idestatus, @PathVariable("llave") String llave,
            RedirectAttributes redirectAttrs) {
        idestatus = idestatus == 2 ? 1 : 2;
        int valor = kcatalogoService.deleteKcatalogo(llave, idestatus);
        if (valor >= 1) {
            msg.success("Ejecutado correctamente", redirectAttrs);
            System.out.println("se elimino registro: " + llave);
        } else {
            msg.danger("No se pudo ejecutar", redirectAttrs);
            System.err.println("no se elimino registro");
        }
        return "redirect:/kcatalogos/principal";
    }

}

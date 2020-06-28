/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.roles;

import com.app.riife.formasMenu.FormasMenu;
import com.app.riife.inicio.SessionComponent;
import com.app.riife.util.Mensaje;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author usuario
 */
@Controller
public class RolesControl {

    private final RolesService rolesService;
    private List<Roles> roles;
    private List<FormasMenu> formas;
    private Roles rol;
    private final Mensaje msg = new Mensaje();

    @Autowired
    public RolesControl(RolesService rolesService) {
        this.rolesService = rolesService;
    }
    
    @GetMapping("roles")
    public String listar(Model model) {
        roles = rolesService.listAll();
        model.addAttribute("lista", roles);
        return "roles/principal";
    }

    @GetMapping("roles/agregar")
    public String agregar(Model model) {
        model.addAttribute(new Roles());
        return "roles/agregar";
    }

    @PostMapping(value = "roles/add")
    public String agregar(Roles rol, RedirectAttributes redirectAttrs) {
        rol = Roles.inicializarAdd(rol);
        msg.crearMensaje(rolesService.add(rol), redirectAttrs);
        
        return "redirect:/roles";
    }

    @GetMapping(value = "roles/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        rol = rolesService.get(id);
        String validUrl = Objects.isNull(rol) ? "redirect:/roles" : "roles/editar";
        model.addAttribute("rol", rol);
        return validUrl;
    }

    @PostMapping(value = "roles/update/{id}")
    public String editar(@PathVariable("id") int id, Roles rol, RedirectAttributes redirectAttrs) {
        rol.setNoRol(id);
        rol = Roles.inicializarEdit(rol);
        msg.crearMensaje(rolesService.update(rol), redirectAttrs);
        
        return "redirect:/roles";
    }

    @GetMapping("roles/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        msg.crearMensaje(rolesService.delete(id), redirectAttrs);
        return "redirect:/roles";
    }

    @GetMapping(value = "roles/asignar/{id}")
    public String asignar(@PathVariable("id") int id, Model model) {
        rol = rolesService.get(id);
        String validUrl = "redirect:/roles";
        if(Objects.nonNull(rol)){
        model.addAttribute("noRol", id);
        RolFormas rolFormas = new RolFormas();
        formas = rolesService.listFormasById(id);
        rolFormas.setFormas(formas);
        model.addAttribute("rolFormas", rolFormas);
        validUrl = "roles/asignar";
        }
        return validUrl;
    }

    @PostMapping(value = "roles/updateAgregarFormas/{id}")
    public String asignar(@PathVariable("id") int id, @ModelAttribute("rolFormas") RolFormas rolFormas,
            RedirectAttributes redirectAttrs) {
        rolFormas.setNoRol(id);
        msg.crearMensaje(rolesService.assignFormaMenuToRol(rolFormas), redirectAttrs);
        return "redirect:/roles";
    }

}

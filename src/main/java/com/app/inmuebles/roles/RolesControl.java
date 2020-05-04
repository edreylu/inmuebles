/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.roles;

import com.app.inmuebles.util.SessionControl;
import com.app.inmuebles.formasMenu.FormasMenu;
import com.app.inmuebles.util.Mensaje;
import com.app.inmuebles.roles.RolFormas;
import com.app.inmuebles.roles.Roles;
import com.app.inmuebles.roles.RolesService;
import java.util.Iterator;
import java.util.List;
import javax.validation.Valid;
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

    @Autowired
    SessionControl session;
    @Autowired
    private RolesService rolesService;
    List<Roles> roles;
    List<FormasMenu> formas;
    Roles rol;
    List lista;
    int id;
    Mensaje msg = new Mensaje();

    @GetMapping("roles/principal")
    public String listar(Model model) {
        roles = rolesService.listAll();
        model.addAttribute("lista", roles);
        return session.url("roles/principal");
    }

    @GetMapping("roles/agregar")
    public String agregar(Model model) {
        model.addAttribute(new Roles());
        return session.url("roles/agregar");
    }

    @PostMapping(value = "roles/add")
    public String agregar(Roles rol, RedirectAttributes redirectAttrs) {
        rol.setInsertar("N");
        rol.setActualizar("N");
        rol.setEliminar("N");
        rol.setConsultar("N");
        rol.setDescargar("S");
        if (rol.getInsertarSel() != null) {
            rol.setInsertar("S");
        }
        if (rol.getActualizarSel() != null) {
            rol.setActualizar("S");
        }
        if (rol.getEliminarSel() != null) {
            rol.setEliminar("S");
        }
        if (rol.getConsultarSel() != null) {
            rol.setConsultar("S");
        }

        int valor = rolesService.addRole(rol);
        if (valor >= 1) {
            msg.success("Agregado correctamente", redirectAttrs);
            System.out.println("se agrego registro: " + valor);
        } else {
            msg.danger("No se pudo agregar", redirectAttrs);
            System.err.println("no se agrego registro");
        }
        return "redirect:/roles/principal";
    }

    @GetMapping(value = "roles/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        rol = rolesService.getRole(id);
        model.addAttribute("rol", rol);
        return session.url("roles/editar");
    }

    @PostMapping(value = "roles/update/{id}")
    public String editar(@PathVariable("id") int id, @Valid Roles rol, RedirectAttributes redirectAttrs) {
        rol.setNoRol(id);
        rol.setInsertar("N");
        rol.setActualizar("N");
        rol.setEliminar("N");
        rol.setConsultar("N");
        rol.setDescargar("S");
        if (rol.getInsertarSel().equals(true)) {
            rol.setInsertar("S");
        }
        if (rol.getActualizarSel().equals(true)) {
            rol.setActualizar("S");
        }
        if (rol.getEliminarSel().equals(true)) {
            rol.setEliminar("S");
        }
        if (rol.getConsultarSel().equals(true)) {
            rol.setConsultar("S");
        }

        int valor = rolesService.editRole(rol);
        if (valor >= 1) {
            msg.success("Editado correctamente", redirectAttrs);
            System.out.println("se edito registro: " + valor);
        } else {
            msg.danger("No se pudo editar", redirectAttrs);
            System.err.println("no se edito registro");
        }
        return "redirect:/roles/principal";
    }

    @GetMapping("roles/eliminar/{id}")
    public String eliminar(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        boolean rolUsuarios = rolesService.existsRolUsuarios(id);
        boolean rolFormas = rolesService.existsRolFormas(id);
        if (rolUsuarios) {
            msg.danger("No se pudo eliminar rol por que esta en uso", redirectAttrs);
            System.err.println("no se elimino registro");
        } else if (rolFormas) {
            msg.danger("No se pudo eliminar rol por que tiene formas asignadas", redirectAttrs);
            System.err.println("no se elimino registro");
        } else {
            int valor = rolesService.deleteRole(id);
            if (valor >= 1) {
                msg.success("Eliminado correctamente", redirectAttrs);
                System.out.println("se elimino registro: " + id);
            } else {
                msg.danger("No se pudo eliminar", redirectAttrs);
                System.err.println("no se elimino registro");
            }
        }
        return "redirect:/roles/principal";
    }

    @GetMapping(value = "roles/asignar/{id}")
    public String asignar(@PathVariable("id") int id, Model model) {
        rol = rolesService.getRole(id);
        model.addAttribute("noRol", id);
        RolFormas formasRol = new RolFormas();
        formas = rolesService.getRegistrosFormas(id);
        formasRol.setFormas(formas);
        formasRol.setRol(rol.getDescripcion());
        model.addAttribute("formasRol", formasRol);
        return session.url("roles/asignar");
    }

    @PostMapping(value = "roles/updateAgregarFormas/{id}")
    public String asignar(@PathVariable("id") int id, @ModelAttribute("formasRol") RolFormas fr,
            RedirectAttributes redirectAttrs) {

        boolean existe = rolesService.existsRolFormas(id);
        if (existe) {
            rolesService.deleteFormaMenu(id);
        }
        for (Iterator it = fr.getFormas().iterator(); it.hasNext();) {
            FormasMenu x = (FormasMenu) it.next();
            if (x.isMenuSeleccionado()) {
                rolesService.assignFormaMenu(id, x.getNoFormaMenu());
            }
            if (!it.hasNext()) {
                msg.success("Pantallas asignadas correctamente", redirectAttrs);
            }
        }
        return "redirect:/roles/principal";
    }

}

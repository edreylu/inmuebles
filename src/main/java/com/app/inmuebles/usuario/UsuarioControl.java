/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.usuario;

import com.app.inmuebles.util.SessionControl;
import com.app.inmuebles.util.Mensaje;
import com.app.inmuebles.roles.Roles;
import com.app.inmuebles.usuario.Usuario;
import com.app.inmuebles.roles.RolesService;
import com.app.inmuebles.usuario.UsuarioService;
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
public class UsuarioControl {

    @Autowired
    SessionControl session;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RolesService rolesService;
    List<Usuario> datos;
    List<Roles> roles;
    Usuario usuario;
    List lista;
    int id;
    Mensaje msg = new Mensaje();

    @GetMapping("usuarios/principal")
    public String listar(Model model) {
        datos = usuarioService.listAll();
        model.addAttribute("lista", datos);
        return session.url("usuarios/principal");
    }

    @GetMapping("usuarios/agregar")
    public String agregar(Model model) {
        model.addAttribute(new Usuario());
        roles = rolesService.listAll();
        model.addAttribute("roles", roles);
        return session.url("usuarios/agregar");
    }

    @PostMapping(value = "usuarios/add")
    public String agregar(Usuario us, RedirectAttributes redirectAttrs) {
        int valor = usuarioService.addUsuario(us);
        if (valor >= 1) {
            usuarioService.deleteRolUsuario(us.getNoUsuario());
            usuarioService.assignRolUsuario(us.getNoUsuario(), us.getRol().getNoRol());
            msg.success("Agregado correctamente", redirectAttrs);
            System.out.println("se agrego registro: " + valor);
        } else {
            msg.danger("No se pudo agregar", redirectAttrs);
            System.err.println("no se agrego registro");
        }
        return "redirect:/usuarios/principal";
    }

    @GetMapping(value = "usuarios/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        usuario = usuarioService.getUsuario(id);
        model.addAttribute("usuario", usuario);
        roles = rolesService.listAll();
        model.addAttribute("roles", roles);
        return session.url("usuarios/editar");
    }

    @PostMapping(value = "usuarios/update/{id}")
    public String editar(@PathVariable("id") int id, @Valid Usuario us, RedirectAttributes redirectAttrs) {
        us.setNoUsuario(id);
        int valor = usuarioService.editUsuario(us);
        if (valor >= 1) {
            usuarioService.deleteRolUsuario(us.getNoUsuario());
            usuarioService.assignRolUsuario(us.getNoUsuario(), us.getRol().getNoRol());
            msg.success("Editado correctamente", redirectAttrs);
            System.out.println("se edito registro: " + valor);
        } else {
            msg.danger("No se pudo editar", redirectAttrs);
            System.err.println("no se edito registro");
        }
        return "redirect:/usuarios/principal";
    }

    @GetMapping("usuarios/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus, RedirectAttributes redirectAttrs) {

        int valor = usuarioService.deleteUsuario(id, idestatus);
        if (valor >= 1) {
            boolean existe = usuarioService.existsRolUsuario(id);
            if (existe) {
                usuarioService.deleteRolUsuario(id);
            }
            msg.success("Ejecutado correctamente", redirectAttrs);
            System.out.println("se elimino registro: " + id);
        } else {
            msg.danger("No se pudo ejecutar", redirectAttrs);
            System.err.println("no se elimino registro");
        }
        return "redirect:/usuarios/principal";
    }

    @GetMapping(value = "usuarios/updatePassword/{id}")
    public String modificarPasaporte(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        int valor = usuarioService.resetPasaporte(id);
        if (valor == 1) {
            msg.success("Actualizado correctamente", redirectAttrs);
        } else {
            msg.danger("No se pudo Actualizar", redirectAttrs);
        }
        return "redirect:/usuarios/principal";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.usuario;

import com.app.riife.util.Mensaje;
import com.app.riife.roles.Roles;
import com.app.riife.roles.RolesService;
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
public class UsuarioControl {

    private final UsuarioService usuarioService;
    private final RolesService rolesService;
    private List<Usuario> usuarios;
    private List<Roles> roles;
    private Usuario usuario;
    private final Mensaje msg = new Mensaje();

    @Autowired
    public UsuarioControl(UsuarioService usuarioService, RolesService rolesService) {
        this.usuarioService = usuarioService;
        this.rolesService = rolesService;
    }

    @GetMapping("/usuarios")
    public String listar(Model model) {
        usuarios = usuarioService.listAll();
        model.addAttribute("lista", usuarios);
        return "usuarios/principal";
    }

    @GetMapping("usuarios/agregar")
    public String agregar(Model model) {
        model.addAttribute(new Usuario());
        roles = rolesService.listAll();
        model.addAttribute("roles", roles);
        return "usuarios/agregar";
    }

    @PostMapping(value = "usuarios/add")
    public String agregar(Usuario us, RedirectAttributes redirectAttrs) {
        msg.crearMensaje(usuarioService.add(us), redirectAttrs);
        
        return "redirect:/usuarios";
    }

    @GetMapping(value = "usuarios/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        usuario = usuarioService.get(id);
        String validUrl = "redirect:/usuarios";
        if(Objects.nonNull(usuario)){
        model.addAttribute("usuario", usuario);
        roles = rolesService.listAll();
        model.addAttribute("roles", roles);
        validUrl = "usuarios/editar";
        }
        return validUrl;
    }

    @PostMapping(value = "usuarios/update/{id}")
    public String editar(@PathVariable("id") int id, Usuario us, RedirectAttributes redirectAttrs) {
        us.setNoUsuario(id);
        msg.crearMensaje(usuarioService.update(us), redirectAttrs);
        
        return "redirect:/usuarios";
    }

    @GetMapping("usuarios/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus, 
            RedirectAttributes redirectAttrs) {
        msg.crearMensaje(usuarioService.delete(id, idestatus), redirectAttrs);
        
        return "redirect:/usuarios";
    }

    @GetMapping(value = "usuarios/updatePassword/{id}")
    public String modificarPasaporte(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
        msg.crearMensaje(usuarioService.resetPass(id), redirectAttrs);
        return "redirect:/usuarios";
    }
}

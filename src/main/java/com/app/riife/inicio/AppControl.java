package com.app.riife.inicio;

import com.app.riife.util.Mensaje;
import com.app.riife.usuario.Usuario;
import com.app.riife.usuario.UsuarioService;
import java.sql.SQLException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppControl implements NavBarUtil {

    private final SessionComponent session;
    private final UsuarioService usuarioService;
    private Usuario usuario;
    Mensaje msg = new Mensaje();
    @Value("${google.recaptcha.sitio}")
    private String key;

    @Autowired
    public AppControl(SessionComponent session, UsuarioService usuarioService) {
        this.session = session;
        this.usuarioService = usuarioService;
    }

    //accesos a aplicacion desde el login hacia el menu cuando si accede y 403 cuando no hay permisos.
    @GetMapping("")
    public String main(Model model) {
        return "index";
    }

    @GetMapping("login")
    public String index(Model model) {
        model.addAttribute("key", key);
        return "/login";
    }

    @PostMapping(value = "loginProcess")
    public String login(@ModelAttribute("login") Login login) throws SQLException {
        String url = "redirect:/menu";
        if (!Objects.equals(login.getUsuario(), login.getContraseña())) {
            usuario = usuarioService.exists(login);
            if (usuario.getNoUsuario() > 0) {
                session.createSession(usuario);
            } else {
                url = "redirect:/login?error=true";
            }
        } else {
            url = "redirect:/login?iguales=true";
        }

        return url;
    }

    @GetMapping("menu")
    public String menu(Model model) {
        Usuario usuarioMenu = session.getUsuario();
        model.addAttribute("usuario", usuarioMenu);
        return "/menu";
    }

    @GetMapping("/logout")
    public String logout() {
        session.closeSession();
        return "redirect:/";
    }

    @PostMapping(value = "loginCambiar")
    public String cambiar(@ModelAttribute("login") Login login, RedirectAttributes redirectAttrs) {
        msg.crearMensaje(usuarioService.changePass(login), redirectAttrs);
        return "redirect:/login";
    }

}

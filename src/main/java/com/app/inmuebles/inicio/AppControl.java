package com.app.inmuebles.inicio;

import com.app.inmuebles.formasMenu.FormasMenu;
import com.app.inmuebles.util.Login;
import com.app.inmuebles.util.Mensaje;
import com.app.inmuebles.formasMenu.FormasMenuService;
import com.app.inmuebles.usuario.Usuario;
import com.app.inmuebles.usuario.UsuarioService;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppControl {

    @Autowired
    HttpSession session;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    FormasMenuService formasMenuService;
    private List<String> pantallas;
    private Usuario usuario;
    private List<FormasMenu> formas;
    Mensaje msg = new Mensaje();
    @Value("${google.recaptcha.secret}")
    private String key;

    //accesos a aplicacion desde el login hacia el menu cuando si accede y 403 cuando no hay permisos.
    @GetMapping("/")
    public String main(Model model) {
        return "index";
    }

    @GetMapping("login")
    public String index(Model model) {
        model.addAttribute("key", key);
        return session.getAttribute("usuario") != null ? "redirect:/menu" : "/login";
    }

    @PostMapping(value = "loginProcess")
    public String login(@ModelAttribute("login") Login login) throws SQLException {
        String url = "";
        if (!login.getUsuario().equals(login.getContraseña())) {
            usuario = usuarioService.existsUsuario(login.getUsuario(), login.getContraseña());
            if (login.getUsuario() != null && login.getContraseña() != null && usuario.getNoUsuario() > 0) {
                url = "redirect:/menu";
                pantallas = formasMenuService.getPermisoPantalla(usuario.getNoUsuario());
                formas = formasMenuService.getMenu(usuario.getNoUsuario());
                String html = menuHtml();
                session.setAttribute("usuario", usuario);
                session.setAttribute("pantallas", pantallas);
                session.setAttribute("html", html);
                session.setAttribute("titulo", "MENU");
            } else {
                url = "redirect:/login?error=true";
            }
        } else {
            url = "redirect:/login?iguales=true";
        }

        return url;
    }

    @GetMapping("menu")
    public String menu() {
        return session.getAttribute("usuario") != null ? "/menu" : "redirect:/login";
    }

    @GetMapping("403")
    public String Error403() {
        return "403";
    }

    @GetMapping("404")
    public String Error404() {
        return "404";
    }

    @GetMapping("/logout")
    public String logout() {
        System.out.println("com.app.curso.controller.AppControl.logout()");
        session.removeAttribute("usuario");
        session.removeAttribute("pantallas");
        session.removeAttribute("html");
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping(value = "loginCambiar")
    public String cambiar(@ModelAttribute("login") Login login, RedirectAttributes redirectAttrs){
        boolean isValidUser = false;
        int valor = 0;
        if (login.getContraseña().equals(login.getContraseña2())) {
            msg.danger("Contraseña actual y nueva no pueden ser iguales", redirectAttrs);
            System.out.println("contraseñas iguales");
        } else if (login.getUsuario().equals(login.getContraseña2())) {
            msg.danger("Contraseña nueva y usuario no pueden ser iguales", redirectAttrs);
            System.out.println("contraseña nueva y usuario iguales");
        } else {
            usuario = usuarioService.existsUsuario(login.getUsuario(), login.getContraseña());
            if (login.getUsuario() != null && login.getContraseña() != null && usuario.getNoUsuario() > 0) {
                isValidUser = true;
                valor = usuarioService.changePasaporte(usuario.getNoUsuario(), login.getContraseña2());
                isValidUser = valor > 0;
                if (isValidUser) {
                    msg.success("Cambiado correctamente", redirectAttrs);
                    System.out.println("se actualizo registro: ");
                } else {
                    msg.danger("No se pudo Actualizar Password", redirectAttrs);
                    System.out.println("NO se actualizo registro: ");
                }
            } else {
                msg.danger("No existe Usuario", redirectAttrs);
                System.out.println("NO existe Usuario");
            }
        }

        return "redirect:/login";
    }

    public String menuHtml() {
        StringBuilder html = new StringBuilder();
        String nombrePapa = "";
        int contador = 0;
        String menu = "inmuebles/menu";
        for (FormasMenu men : formas) {
            if (contador == 0) {
                html.append(" <ul class=\"navbar-nav\"> \n"
                        + "<a class=\"navbar-brand\" href=\"/" + menu + "\"><i class=\"fas fa-home\"></i></a>\n");
            }
            if (!men.getNombrePapa().equals(nombrePapa)) {

                html.append(" <li class=\"nav-item dropdown\">\n"
                        + "        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbarDropdownMenuLink\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"> <i class='" + men.getIconoPapa() + "'></i>" + men.getNombrePapa() + "</a> \n"
                        + "        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdownMenuLink\">");
            }
            men.setUrl("inmuebles/" + men.getUrl() + "/principal");
                html.append(" <a class=\"dropdown-item\" href=\"/").append(men.getUrl()).append("\"><i class='").append(men.getIcono()).append("'></i>").append(men.getTitulo()).append("</a> \n");

            if (!men.getNombrePapa().equals(nombrePapa)) {
                nombrePapa = men.getNombrePapa();

            }
            if (!formas.get(contador).getNombrePapa()
                    .equals(formas.get(contador < formas.size() - 1 ? contador + 1 : contador).getNombrePapa()) 
                    || contador + 1 == formas.size()) {
                
                html.append("  </div> </li> \n");

            }
            if (formas.size() == contador + 1) {
                        html.append("</ul> \n <ul class=\"navbar-nav ml-auto nav-flex-icons\">\n"
                        + "      <li class=\"nav-item dropdown\">\n"
                        + "        <a class=\"nav-link dropdown-toggle\" id=\"navbarDropdownMenuLink-333\" data-toggle=\"dropdown\"\n"
                        + "          aria-haspopup=\"true\" aria-expanded=\"false\">\n"
                        + "          <i class=\"fas fa-user\"> " + usuario.getNomUsuario() + "</i>\n"
                        + "        </a>\n"
                        + "        <div class=\"dropdown-menu dropdown-menu-right dropdown-default\"\n"
                        + "          aria-labelledby=\"navbarDropdownMenuLink-333\">\n"
                        + "          <a class=\"dropdown-item\" onclick=\"mensajeCerrarSesion()\"><i class=\"fas fa-share-square\"></i> Cerrar Sesion</a>\n"
                        + "        </div>\n"
                        + "      </li>\n"
                        + "    </ul>");
            }

            contador++;
        }
        
        return html.toString();
    }
}

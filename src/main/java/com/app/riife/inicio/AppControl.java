package com.app.riife.inicio;

import com.app.riife.formasMenu.FormasMenu;
import com.app.riife.util.Mensaje;
import com.app.riife.formasMenu.FormasMenuService;
import static com.app.riife.inicio.NavBarUtil.BEGIN_NAV;
import static com.app.riife.inicio.NavBarUtil.CHILD_LINKS;
import static com.app.riife.inicio.NavBarUtil.FATHER;
import static com.app.riife.inicio.NavBarUtil.FINAL_FATHER;
import com.app.riife.usuario.Usuario;
import com.app.riife.usuario.UsuarioService;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
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
public class AppControl implements NavBarUtil{

    private final SessionComponent session;
    private final UsuarioService usuarioService;
    private final FormasMenuService formasMenuService;
    private Usuario usuario;
    Mensaje msg = new Mensaje();
    @Value("${google.recaptcha.sitio}")
    private String key;
    @Value("${server.servlet.contextPath}")
    private String contextPath;
    
    @Autowired
    public AppControl(SessionComponent session, UsuarioService usuarioService, FormasMenuService formasMenuService) {
        this.session = session;
        this.usuarioService = usuarioService;
        this.formasMenuService = formasMenuService;
    }

    //accesos a aplicacion desde el login hacia el menu cuando si accede y 403 cuando no hay permisos.
    @GetMapping("/")
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
                List<String> pantallas = formasMenuService.getPermissionToPages(usuario.getNoUsuario());
                List<FormasMenu> formas = formasMenuService.getMenu(usuario.getNoUsuario());
                String html = menuHtml(formas);
                session.setAttribute("usuario", usuario);
                session.setAttribute("pantallas", pantallas);
                session.setAttribute("html", html);
                session.setAttribute("titulo", "MENU");
            } else {
                url = "redirect:/login?error=true";
            }
        }
        else {
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

    public String menuHtml(List<FormasMenu> formas) {
        
        StringBuilder html = new StringBuilder();
        int contador = 1, sizeList = formas.size();
        String menu = "riife/menu", nombrePapa = "";
        
        for (FormasMenu forma : formas) {
            
            //inicio del navbar
            if (contador == 1)
                html.append(String.format(BEGIN_NAV, menu));
            
            //creacion de los padres o titulos principales
            if (!Objects.equals(forma.getNombrePapa(), nombrePapa))
                html.append(String.format(FATHER, forma.getIconoPapa(),
                        forma.getNombrePapa()));
            
            //creacion de los hijos u opciones que contienen url
            html.append(String.format(CHILD_LINKS, contextPath,
                    forma.getUrl(),
                    forma.getIcono(),
                    forma.getTitulo()));
            
            if (!Objects.equals(forma.getNombrePapa(),
                    nombrePapa)) 
                nombrePapa = forma.getNombrePapa();
            
            //Se cierra padre cuando se acaben las ligas hijas para continuar con otro padre o finalizar.
            int idProximaForma = contador >= sizeList ? contador - 1 : contador;
            if (!Objects.equals(forma.getNombrePapa(), formas.get(idProximaForma).getNombrePapa())
                    || contador == sizeList) 
                html.append(FINAL_FATHER);
            
            //si el contador + 1 es igual al tamaño de la lista de opciones construye el final del navbar con el username.
            if (sizeList == contador) 
                html.append(String.format(NavBarUtil.USER_AND_FINISH_NAV, usuario.getNomUsuario()));

            contador++;
        }

        return html.toString();
    }

}

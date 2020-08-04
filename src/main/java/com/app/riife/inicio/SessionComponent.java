/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.inicio;

import com.app.riife.formasMenu.FormasMenu;
import com.app.riife.formasMenu.FormasMenuService;
import static com.app.riife.inicio.NavBarUtil.BEGIN_NAV;
import static com.app.riife.inicio.NavBarUtil.CHILD_LINKS;
import static com.app.riife.inicio.NavBarUtil.FATHER;
import static com.app.riife.inicio.NavBarUtil.FINAL_FATHER;
import com.app.riife.usuario.Usuario;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component
public class SessionComponent {

    private final FormasMenuService formasMenuService;
    private final HttpSession session;
    private Usuario usuario;
    private static final Logger LOG = Logger.getLogger(SessionComponent.class.getName());
    @Value("${server.servlet.contextPath}")
    private String contextPath;

    @Autowired
    public SessionComponent(FormasMenuService formasMenuService, HttpSession session) {
        this.formasMenuService = formasMenuService;
        this.session = session;
    }

    protected void createSession(Usuario usuarioSession) {
        usuario = usuarioSession;
        List<String> pantallas = formasMenuService.getPermissionToPages(usuario.getNoUsuario());
        List<FormasMenu> formas = formasMenuService.getMenu(usuario.getNoUsuario());
        String html = menuHtml(formas);
        session.setAttribute("usuario", usuario);
        session.setAttribute("pantallas", pantallas);
        session.setAttribute("html", html);
        session.setAttribute("titulo", "MENU");
        session.setMaxInactiveInterval(5 * 60);
        LOG.log(Level.INFO, "logging: {0}", usuario.getClave());
    }

    protected void closeSession() {
        LOG.log(Level.INFO, "Logout: {0}", usuario.getClave());
        session.removeAttribute("usuario");
        session.removeAttribute("pantallas");
        session.removeAttribute("html");
        session.invalidate();
    }

    public Usuario getUsuario() {
        usuario = Objects.nonNull(session.getAttribute("usuario"))
                ? (Usuario) session.getAttribute("usuario")
                : new Usuario();
        return usuario;
    }

    public final int noUsuarioActivo() {
        return getUsuario().getNoUsuario();
    }

    public String menuHtml(List<FormasMenu> formas) {

        StringBuilder html = new StringBuilder();
        int contador = 1, sizeList = formas.size();
        String menu = "riife/menu", nombrePapa = "";

        for (FormasMenu forma : formas) {

            //inicio del navbar
            if (contador == 1) {
                html.append(String.format(BEGIN_NAV, menu));
            }

            //creacion de los padres o titulos principales
            if (!Objects.equals(forma.getNombrePapa(), nombrePapa)) {
                html.append(String.format(FATHER, forma.getIconoPapa(),
                        forma.getNombrePapa()));
            }

            //creacion de los hijos u opciones que contienen url
            html.append(String.format(CHILD_LINKS, contextPath,
                    forma.getUrl(),
                    forma.getIcono(),
                    forma.getTitulo()));

            if (!Objects.equals(forma.getNombrePapa(),
                    nombrePapa)) {
                nombrePapa = forma.getNombrePapa();
            }

            //Se cierra padre cuando se acaben las ligas hijas para continuar con otro padre o finalizar.
            int idProximaForma = contador >= sizeList ? contador - 1 : contador;
            if (!Objects.equals(forma.getNombrePapa(), formas.get(idProximaForma).getNombrePapa())
                    || contador == sizeList) {
                html.append(FINAL_FATHER);
            }

            //si el contador + 1 es igual al tamaño de la lista de opciones construye el final del navbar con el username.
            if (sizeList == contador) {
                html.append(String.format(NavBarUtil.USER_AND_FINISH_NAV, usuario.getNomUsuario()));
            }

            contador++;
        }

        return html.toString();
    }
}

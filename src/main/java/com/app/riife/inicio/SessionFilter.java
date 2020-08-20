/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.inicio;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Edward Reyes
 */
@WebFilter(urlPatterns = {"/capitulos/*", "/cuestionarios/*", "/encuestas/*", "/formas/*",
    "/kcatalogos/*", "/preguntas/*", "/roles/*", "/subcapitulos/*", "/usuarios/*", "/menu", "/login"},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})

public class SessionFilter implements Filter {

    //private static final Logger LOG = Logger.getLogger(SessionFilter.class.getName());
    private FilterConfig config = null;

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        config.getServletContext().log("Initializing SessionFilter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        boolean isAuthenticated = Objects.nonNull(httpRequest.getSession().getAttribute("usuario"));
        boolean existsForma;

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        if (isAuthenticated) {
            //Usuario usuario = (Usuario) httpRequest.getSession().getAttribute("usuario");
            //LOG.log(Level.INFO, "Usuario: {0} RequestURI: {1}", new Object[]{usuario.getClave(),requestURI});
            @SuppressWarnings("unchecked")
			List<String> pantallas = (List<String>) httpRequest.getSession().getAttribute("pantallas");
            pantallas.add("menu");
            existsForma = findForma(pantallas, requestURI);

            if (!existsForma) {
                httpResponse.sendRedirect(contextPath + "/menu");
            }

        } else {
            if (!requestURI.contains("/login")) {
                //LOG.log(Level.INFO, "RequestURI: {0}", requestURI);
                httpResponse.sendRedirect(contextPath + "/login");
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        config.getServletContext().log("Destroying SessionFilter");
    }

    private boolean findForma(List<String> resources, String requestURI) {
        Optional<String> findObject = resources.stream()
                .filter((resource) -> {
                    return requestURI.contains(resource);
                }).findFirst();
        return findObject.isPresent();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.objectHtml;

import com.app.riife.kcatalogo.Kcatalogo;
import com.app.riife.kcatalogo.KcatalogoService;
import com.app.riife.pregunta.Pregunta;
import com.app.riife.respuesta.Respuesta;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Edward Reyes
 */
public class OpcionMultipleSinEspecificar implements ObjectHtml {

    private final KcatalogoService kcatalogoService;
    
    @Autowired
    public OpcionMultipleSinEspecificar(KcatalogoService kcatalogoService) {
        this.kcatalogoService = kcatalogoService;
    }

    @Override
    public String create(Respuesta respuesta) {
        StringBuilder part = new StringBuilder();
        Pregunta pregunta = respuesta.getPregunta();
        List<Kcatalogo> catalogos = kcatalogoService.listCatalogoEncuesta(pregunta.getCatalogo());
        String respuestaNormal = Objects.isNull(respuesta.getRespuesta()) ? "" : respuesta.getRespuesta();
        
        String fragmentoPregunta = pregunta.getCabeceraPregunta();
        part.append(fragmentoPregunta);
        
        for (Kcatalogo catalogo : catalogos) {
            String checked = respuestaNormal.contains(catalogo.getDescripcion()) ? "checked" : "";
            fragmentoPregunta = " <section class=\"row\">\n"
                    + " <div class=\"col-md-8\">\n"
                    + " <div class=\"form-group\">\n"
                    + " <input type=\"checkbox\" "
                    + "id=\"respuestas[" + respuesta.getNoRespuesta() + "].respuesta\" "
                    + " value=\"" + catalogo.getDescripcion() + "\" "
                    + " name=\"respuestas[" + respuesta.getNoRespuesta() + "].respuesta\" "
                    + " maxlength=\"" + pregunta.getLongitudMaximaxCatalogo() + "\" "
                    + " aria-label=\"Checkbox for following text input\" " 
                    + checked + ">\n"
                    + " <label for=\"respuestas[" + respuesta.getNoRespuesta() + "].respuesta\">"
                    + catalogo.getDescripcion() 
                    + " </label>\n"
                    + " </div>\n"
                    + " </div>\n"
                    + " </section>";
            part.append(fragmentoPregunta);
        }
        
        fragmentoPregunta = "</div></div><br/>";
        part.append(fragmentoPregunta);
        
        return part.toString();
    }

}

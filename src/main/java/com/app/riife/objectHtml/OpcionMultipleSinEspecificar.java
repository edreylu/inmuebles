/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.objectHtml;

import com.app.riife.kcatalogo.Kcatalogo;
import com.app.riife.pregunta.Pregunta;
import com.app.riife.respuesta.Respuesta;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Edward Reyes
 */
public class OpcionMultipleSinEspecificar implements ObjectHtml{

    private Pregunta pregunta;
    private String fragmentoPregunta,checked, respuestaNormal;
    private StringBuilder part;
    
    @Override
    public String create(List<Kcatalogo> catalogos, Respuesta respuesta, int noRespuesta) {
        part = new StringBuilder();
        pregunta = respuesta.getPregunta();
        respuestaNormal = Objects.isNull(respuesta.getRespuesta()) ? "" : respuesta.getRespuesta();
        fragmentoPregunta = pregunta.getCabeceraPregunta();
        part.append(fragmentoPregunta);
        for (Kcatalogo catalogo : catalogos) {
            checked = respuestaNormal.contains(catalogo.getDescripcion()) ? "checked" : "";
            fragmentoPregunta = "<section class=\"row\">\n"
                    + "   <div class=\"col-md-8\">\n"
                    + "   <div class=\"form-group\">\n"
                    + "   <input type=\"checkbox\" id=\"respuestas[" + noRespuesta + "].respuesta\" "
                    + "     value=\"" + catalogo.getDescripcion() + "\" "
                    + "     name=\"respuestas[" + noRespuesta + "].respuesta\" "
                    + "     maxlength=\"" + pregunta.getLongitudMaximaxCatalogo() + "\" "
                    + "     aria-label=\"Checkbox for following text input\" " + checked + ">\n"
                    + "   <label for=\"respuestas[" + noRespuesta + "].respuesta\">"
                    + catalogo.getDescripcion() + "</label>\n"
                    + "   </div>\n"
                    + "   </div>\n"
                    + "  </section>";
            part.append(fragmentoPregunta);
        }
        fragmentoPregunta = "</div></div><br/>";
        part.append(fragmentoPregunta);
        return part.toString();    
    }
    
}

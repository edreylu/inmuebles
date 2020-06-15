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
public class ListaDesplegableEspecificarOtro implements ObjectHtml{
    
    private Pregunta pregunta;
    private String fragmentoPregunta,respuestaNormal,respuestaEspecifica,selected,display;
    private StringBuilder part;

    @Override
    public String create(List<Kcatalogo> catalogos, Respuesta respuesta, int noRespuesta) {
        part = new StringBuilder();
        pregunta = respuesta.getPregunta();
        respuestaNormal = Objects.isNull(respuesta.getRespuesta()) ? "" : respuesta.getRespuesta();
        respuestaEspecifica = Objects.isNull(respuesta.getRespuestaEspecifica()) ? ""
                : respuesta.getRespuestaEspecifica();
        fragmentoPregunta = pregunta.getCabeceraPregunta()
                + " <section class=\"row\">\n"
                + "          <div class=\"col-md-8\">\n"
                + "            <select class=\"form-control\" "
                + "   onchange=\"getOtroEspecificar(this," + noRespuesta + ")\" "
                + "   name=\"respuestas[" + noRespuesta + "].respuesta\" "
                + "   id=\"respuestas[" + noRespuesta + "].respuesta\" value=\"" + respuestaNormal
                + "\" required>\n";
        part.append(fragmentoPregunta);
        for (Kcatalogo catalogo : catalogos) {
            selected = respuestaNormal.equalsIgnoreCase(catalogo.getDescripcion()) ? "selected" : "";
            fragmentoPregunta = "  <option value=\"" + catalogo.getDescripcion() + "\" " + selected + ">"
                    + catalogo.getDescripcion() + "</option>\n";
            part.append(fragmentoPregunta);
        }
        display = !respuestaEspecifica.equals("") ? "block" : "none";
        fragmentoPregunta = "  </select> <div class=\"invalid-feedback\">La opcion es requerida</div> \n"
                + "       <input type=\"text\" style=\"display:" + display + "\" "
                + "     id=\"respuestas[" + noRespuesta + "].respuestaEspecifica\" "
                + "     name=\"respuestas[" + noRespuesta + "].respuestaEspecifica\" "
                + "     class=\"form-control\" maxlength=\"128\" value=\"" + respuestaEspecifica + "\""
                + "     placeholder=\"Especificar\" >\n"
                + "        </div>\n"
                + "        </section></div></div><br/>";
        part.append(fragmentoPregunta);
        return part.toString();
    }
    
}

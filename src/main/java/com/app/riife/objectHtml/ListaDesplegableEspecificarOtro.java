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
public class ListaDesplegableEspecificarOtro implements ObjectHtml {

    private final KcatalogoService kcatalogoService;

    @Autowired
    public ListaDesplegableEspecificarOtro(KcatalogoService kcatalogoService) {
        this.kcatalogoService = kcatalogoService;
    }

    @Override
    public String create(Respuesta respuesta) {
        StringBuilder part = new StringBuilder();
        Pregunta pregunta = respuesta.getPregunta();
        List<Kcatalogo> catalogos = kcatalogoService.listCatalogoEncuesta(pregunta.getCatalogo());
        String respuestaNormal = Objects.isNull(respuesta.getRespuesta()) ? "" : respuesta.getRespuesta();
        String respuestaEspecifica = Objects.isNull(respuesta.getRespuestaEspecifica()) ? ""
                : respuesta.getRespuestaEspecifica();

        String fragmentoPregunta = pregunta.getCabeceraPregunta()
                + " <section class=\"row\">\n"
                + " <div class=\"col-md-8\">\n"
                + " <select class=\"form-control\" "
                + " onchange=\"getOtroEspecificar(this," + respuesta.getNoRespuesta() + ")\" "
                + " name=\"respuestas[" + respuesta.getNoRespuesta() + "].respuesta\" "
                + " id=\"respuestas[" + respuesta.getNoRespuesta() + "].respuesta\" "
                + " value=\"" + respuestaNormal
                + "\" required>\n";
        part.append(fragmentoPregunta);

        for (Kcatalogo catalogo : catalogos) {
            String selected = respuestaNormal.equalsIgnoreCase(catalogo.getDescripcion()) ? "selected" : "";
            fragmentoPregunta = " <option value=\"" + catalogo.getDescripcion() + "\" "
                    + selected + ">"
                    + catalogo.getDescripcion()
                    + "</option>\n";
            part.append(fragmentoPregunta);
        }

        String display = !respuestaEspecifica.equals("") ? "block" : "none";

        fragmentoPregunta = " </select>"
                + " <div class=\"invalid-feedback\">La opcion es requerida</div> \n"
                + " <input type=\"text\" "
                + " style=\"display:" + display + "\" "
                + " id=\"respuestas[" + respuesta.getNoRespuesta() + "].respuestaEspecifica\" "
                + " name=\"respuestas[" + respuesta.getNoRespuesta() + "].respuestaEspecifica\" "
                + " class=\"form-control\" "
                + " maxlength=\"128\" "
                + " value=\"" + respuestaEspecifica + "\""
                + " placeholder=\"Especificar\" >\n"
                + " </div>\n"
                + " </section></div></div><br/>";
        part.append(fragmentoPregunta);

        return part.toString();
    }

}

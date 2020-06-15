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
public class OpcionMultipleEspecificarCatalogo implements ObjectHtml{
    
    private Pregunta pregunta;
    private String fragmentoPregunta,checked, respuestaNormal, respuestaEspecifica, display, validaNumericos = "";
    private int maxLenght = 0;
    private StringBuilder part;

    @Override
    public String create(List<Kcatalogo> catalogos, Respuesta respuesta, int noRespuesta) {
        part = new StringBuilder();
        pregunta = respuesta.getPregunta();
        respuestaNormal = Objects.isNull(respuesta.getRespuesta()) ? "" : respuesta.getRespuesta();
        respuestaEspecifica = Objects.isNull(respuesta.getRespuestaEspecifica()) ? ""
                : respuesta.getRespuestaEspecifica();
         //condicionaes para valores de tipo de dato y maxlenght
        if (Objects.nonNull(pregunta.getTipoDeDatoxCatalogo())) {
            validaNumericos = !pregunta.getTipoDeDatoxCatalogo()
                    .equalsIgnoreCase("VARCHAR2") ? "onkeypress='return validaNumericos(event)'" : "";
        }
        if (Objects.nonNull(pregunta.getLongitudMaximaxCatalogo())) {
            maxLenght = pregunta.getLongitudMaximaxCatalogo() == 0
                    ? 10 : pregunta.getLongitudMaximaxCatalogo();
        } else {
            maxLenght = 10;
        }
        fragmentoPregunta = pregunta.getCabeceraPregunta()
                + " <section class=\"row\">\n"
                + "  <div class=\"col-md-8\">\n"
                + "  <div class=\"form-group\">\n";
        part.append(fragmentoPregunta);
        String[] elementosPipe = respuestaEspecifica.split("\\|",-1);
        int contador=0;
        for (Kcatalogo catalogo : catalogos) {
            checked = respuestaNormal.contains(catalogo.getDescripcion()) ? "checked" : "";
            respuestaEspecifica = elementosPipe[contador];
            display = !respuestaEspecifica.equals("") ? "block" : "none";
            fragmentoPregunta = " <div class=\"input-group mb-3\" id=\"check\">\n"
                    + "  <div class=\"input-group-prepend\">\n"
                    + "    <div class=\"input-group-text\">\n"
                    + "     <input type=\"checkbox\" onclick=\"getOmec(\'" + noRespuesta + "',"
                    + "     '" + catalogo.getClaveCatalogo()+ "','" + catalogo.getDescripcion()+ "')\" "
                    + "     value=\"" + catalogo.getDescripcion() + "\" "
                    + "      name=\"respuestas[" + noRespuesta + "].respuesta\" "
                    + "      aria-label=\"Checkbox for following text input\" " + checked + "> \n"
                    + "     <div style=\"font: 12px Arial, Helvetica, sans-serif;\" >"
                    + catalogo.getDescripcion() + ": *</div>\n"
                    + "    </div>\n"
                    + " </div>\n"
                    + " <input type=\"text\" id=\"respuestas[" + noRespuesta + "].respuestaEspecifica_"+catalogo.getClaveCatalogo()+"\" "
                    + " name=\"respuestas[" + noRespuesta + "].respuestaEspecifica\" "
                    + " class=\"form-control\" maxlength=\"" + maxLenght + "\" " + validaNumericos
                    + " value=\"" + respuestaEspecifica + "\" style=\"display:" + display + "\" aria-label=\"Text input with checkbox\">\n"
                    + " </div>\n";
            part.append(fragmentoPregunta);
            contador++;
        }
        fragmentoPregunta = "  </div>\n"
                + " </div>\n"
                + " </section></div></div><br/>";
        part.append(fragmentoPregunta);
        return part.toString();
    }
    
}

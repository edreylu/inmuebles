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
public class OpcionMultipleEspecificarCatalogo implements ObjectHtml {

    private final KcatalogoService kcatalogoService;

    @Autowired
    public OpcionMultipleEspecificarCatalogo(KcatalogoService kcatalogoService) {
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
        int maxLenght = 0;
        String validaNumericos = "";
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

        String fragmentoPregunta = pregunta.getCabeceraPregunta()
                + " <section class=\"row\">\n"
                + "  <div class=\"col-md-8\">\n"
                + "  <div class=\"form-group\">\n";
        part.append(fragmentoPregunta);

        String[] elementsPipe = respuestaEspecifica.split("\\|", -1);
        int contador = 0;

        for (Kcatalogo catalogo : catalogos) {
            String checked = respuestaNormal.contains(catalogo.getDescripcion()) ? "checked" : "";
            respuestaEspecifica = elementsPipe[contador];
            String display = !respuestaEspecifica.equals("") ? "block" : "none";
            String hasCase = catalogo.getDescripcion().equals("NO TIENE") ? "class=\"noTiene_"+respuesta.getNoRespuesta()+"\" " 
                    : "class=\"case_"+respuesta.getNoRespuesta()+"\" ";
            String hasCaseInput = catalogo.getDescripcion().equals("NO TIENE") ? "" : "caseIn_"+respuesta.getNoRespuesta();
            fragmentoPregunta = " <div class=\"input-group mb-3\" id=\"check\">\n"
                    + " <div class=\"input-group-prepend\">\n"
                    + " <div class=\"input-group-text\">\n"
                    + " <input type=\"checkbox\" "
                    + " onclick=\"getOmec(\'"
                    + respuesta.getNoRespuesta() + "',"
                    + " '" + catalogo.getClaveCatalogo() + "','"
                    + catalogo.getDescripcion() + "')\" "
                    + " value=\"" + catalogo.getDescripcion() + "\" "
                    + " name=\"respuestas[" + respuesta.getNoRespuesta() + "].respuesta\" "
                    + hasCase
                    + " aria-label=\"Checkbox for following text input\" "
                    + checked + "> \n"
                    + " <div style=\"font: 12px Arial, Helvetica, sans-serif;\" >"
                    + catalogo.getDescripcion() + ": </div>\n"
                    + " </div>\n"
                    + " </div>\n"
                    + " <input type=\"text\" "
                    + " id=\"respuestas[" + respuesta.getNoRespuesta() + "].respuestaEspecifica_"
                    + catalogo.getClaveCatalogo() + "\" "
                    + " name=\"respuestas[" + respuesta.getNoRespuesta() + "].respuestaEspecifica\" "
                    + " class=\"form-control "+ hasCaseInput+"\" "
                    + " maxlength=\"" + maxLenght + "\" "
                    + validaNumericos
                    + " value=\"" + respuestaEspecifica + "\" "
                    + " style=\"display:" + display + "\" "
                    + " aria-label=\"Text input with checkbox\">\n"
                    + " </div>\n";
            part.append(fragmentoPregunta);

            contador++;
        }
        fragmentoPregunta = " </div>\n"
                + " </div>\n"
                + " </section></div></div><br/>";
        part.append(fragmentoPregunta);

        return part.toString();
    }

}

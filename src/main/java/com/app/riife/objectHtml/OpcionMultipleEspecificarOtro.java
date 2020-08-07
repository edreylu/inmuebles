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
public class OpcionMultipleEspecificarOtro implements ObjectHtml {

    private final KcatalogoService kcatalogoService;
    
    @Autowired
    public OpcionMultipleEspecificarOtro(KcatalogoService kcatalogoService) {
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
        String display = pregunta.getIdPreguntaRef() == 0 ? "block" : "none";
        int maxLenght = 0;
        String validaNumericos = "";
        //condicionaes para valores de tipo de dato y maxlenght
        if (Objects.nonNull(pregunta.getTipoDeDatoOtro())) {
                 validaNumericos = !pregunta.getTipoDeDatoOtro()
                    .equalsIgnoreCase("VARCHAR2") ? "onkeypress='return validaNumericos(event)'" : "";
        }
        if (Objects.nonNull(pregunta.getLongitudMaximaOtro())) {
            maxLenght = pregunta.getLongitudMaximaOtro() == 0
                    ? 10 : pregunta.getLongitudMaximaOtro();
        } else {
            maxLenght = 10;
        }
        
        String fragmentoPregunta = " <div id=\"preguntas[" + respuesta.getNoRespuesta() + "].idPregunta_omeo\" "
                + " style=\"display:" + display + "\">"
                + pregunta.getCabeceraPregunta();
        part.append(fragmentoPregunta);
        
        for (Kcatalogo catalogo : catalogos) {
            String checked = respuestaNormal.contains(catalogo.getDescripcion()) ? "checked" : "";
            fragmentoPregunta = " <section class=\"row\">\n"
                    + " <div class=\"col-md-8\">\n"
                    + " <div class=\"form-group\">\n"
                    + " <input type=\"checkbox\" "
                    + " id=\"respuestas[" + respuesta.getNoRespuesta() + "].respuesta\" "
                    + " onclick=\"getOmeo(\'" 
                    + catalogo.getDescripcion() + "'," 
                    + respuesta.getNoRespuesta() + ")\" "
                    + " value=\"" + catalogo.getDescripcion() + " "
                    + " name=\"respuestas[" + respuesta.getNoRespuesta() + "].respuesta\" "
                    + " aria-label=\"Checkbox for following text input\" " 
                    + checked + ">\n"
                    + " <label for=\"respuestas[" + respuesta.getNoRespuesta() + "].respuesta\">"
                    + catalogo.getDescripcion() 
                    + "</label>\n"
                    + " </div>\n"
                    + " </div>\n"
                    + " </section>";
            part.append(fragmentoPregunta);
        }
        
        display = !respuestaEspecifica.equals("") ? "block" : "none";
        fragmentoPregunta = " <div class=\"col-md-8\">\n"
                + " <div class=\"form-group\">\n"
                + " <input type=\"text\" "
                + " style=\"display:" + display + "\" "
                + " id=\"respuestas[" + respuesta.getNoRespuesta() + "].respuestaEspecifica\" "
                + " name=\"respuestas[" + respuesta.getNoRespuesta() + "].respuestaEspecifica\" "
                + " class=\"form-control\" "
                + " maxlength=\"" + maxLenght + "\" " + validaNumericos
                + " value=\"" + respuestaEspecifica + "\" "
                + " placeholder=\"Especificar\">"
                + "</div></div></div></div><br/></div>";
        part.append(fragmentoPregunta);
        
        return part.toString();
    }

}

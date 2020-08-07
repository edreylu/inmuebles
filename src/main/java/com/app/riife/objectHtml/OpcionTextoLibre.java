/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.objectHtml;

import com.app.riife.pregunta.Pregunta;
import com.app.riife.respuesta.Respuesta;
import java.util.Objects;

/**
 *
 * @author Edward Reyes
 */
public class OpcionTextoLibre implements ObjectHtml {


    @Override
    public String create(Respuesta respuesta) {
        Pregunta pregunta = respuesta.getPregunta();
        String respuestaNormal = Objects.isNull(respuesta.getRespuesta()) ? "" : respuesta.getRespuesta();
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
        
        String fragmentoPregunta = pregunta.getCabeceraPregunta()
                + " <section class=\"row\">\n"
                + " <div class=\"col-md-8\">\n"
                + " <div class=\"form-group\">\n"
                + " <input type=\"text\" "
                + " name=\"respuestas[" + respuesta.getNoRespuesta() + "].respuesta\" "
                + " class=\"form-control\" "
                + " maxlength=\"" + maxLenght + "\" " 
                + validaNumericos
                + " placeholder=\"ESCRIBE RESPUESTA\" "
                + " value=\"" + respuestaNormal + "\" "
                + " required>\n"
                + " <div class=\"invalid-feedback\">La opcion es requerida</div>"
                + " </div>\n"
                + " </div>\n"
                + " </section></div></div><br/> \n";
        return fragmentoPregunta;
    }

}

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
public class OpcionTextoLibre implements ObjectHtml{
    
    private Pregunta pregunta;
    private String fragmentoPregunta,respuestaNormal, validaNumericos="";
    private int maxLenght = 0;

    @Override
    public String create(List<Kcatalogo> catalogos, Respuesta respuesta, int noRespuesta) {
        pregunta = respuesta.getPregunta();
        respuestaNormal = Objects.isNull(respuesta.getRespuesta()) ? "" : respuesta.getRespuesta();
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
        fragmentoPregunta = pregunta.getCabeceraPregunta()
                + "  <section class=\"row\">\n"
                + "          <div class=\"col-md-8\">\n"
                + "            <div class=\"form-group\">\n"
                + "              <input type=\"text\" name=\"respuestas[" + noRespuesta + "].respuesta\" "
                + "   class=\"form-control\" maxlength=\"" + maxLenght + "\" " + validaNumericos
                + "   placeholder=\"ESCRIBE RESPUESTA\" value=\"" + respuestaNormal + "\" required>\n"
                + "            <div class=\"invalid-feedback\">La opcion es requerida</div>"
                + "            </div>\n"
                + "          </div>\n"
                + "  </section></div></div><br/> \n";
        return fragmentoPregunta;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.respuesta;

import com.app.riife.kcatalogo.Kcatalogo;
import com.app.riife.pregunta.Pregunta;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

/**
 *
 * @author Edward Reyes
 */
@Component
public class OpcionHtmlComponent {
    
    private String fragmentoPregunta,
            validaNumericos = "",
            respuestaNormal,
            respuestaEspecifica,
            display,
            checked,
            observaciones,
            selected,
            cabeceraPregunta,
            catalogoPregunta,
            claveCatalogo;
    private Pregunta pregunta;
    private int maxLenght = 0,
            idRespuesta = 0,
            idInmueble = 0,
            idCicloEscolar = 0,
            noUsuario = 0,
            Operacion = 0,
            idRevision = 0,
            noPregunta;
    private StringBuilder part;
    
    public String headerAndOther(Respuesta respuesta, int noRespuesta) {
        initialize(respuesta);
        pregunta = respuesta.getPregunta();
        catalogoPregunta = Objects.isNull(pregunta.getCatalogo()) ? "" : pregunta.getCatalogo();
        claveCatalogo = Objects.isNull(pregunta.getCatalogo()) ? ""
                : pregunta.getCuestionario().getIdCuestionario() + ""
                + pregunta.getCatalogo() + ""
                + pregunta.getClaveCatalogo();
        noPregunta = noRespuesta+1;
        cabeceraPregunta = " <div class=\"card bg-light mb-3\" style=\"width: 100%;\"><div class=\"card-body\">"
                + "        <br/><section class=\"row\">"
                + "        <section class=\"col-md-12\">\n"
                + "        <h6>" + noPregunta + ".- " + pregunta.getPregunta() + "</h6>\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].idRespuesta\" value=" + idRespuesta + " />\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].cuestionario.idCuestionario\" value=" + pregunta.getCuestionario().getIdCuestionario() + " />\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].pregunta.idPregunta\" value=" + pregunta.getIdPregunta() + " />\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].inmueble.idInmueble\" value=" + idInmueble + " />\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].catalogo\" value=\"" + catalogoPregunta + "\" />\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].claveCatalogo\" value=\"" + claveCatalogo + "\" />\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].observaciones\" value=\"" + observaciones + "\"/>\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].cicloEscolar.idCicloEscolar\" value=" + idCicloEscolar + " />\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].idRevision\" value=" + idRevision + " />\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].usuarioRegistro.noUsuario\" value=" + noUsuario + " />\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].operacion\" value=" + Operacion + " />\n"
                + "      <input type=\"hidden\" name=\"respuestas[" + noRespuesta + "].pregunta.opcionMultiple\" value=" + pregunta.getOpcionMultiple() + " />\n"
                + "      </section></section><br/>";
        return cabeceraPregunta;
    }
    
    public String opcionMultipleSinEspecificar(List<Kcatalogo> catalogos, Respuesta respuesta, int noRespuesta) {
        initialize(respuesta);
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

    public String opcionMultipleEspecificarCatalogo(List<Kcatalogo> catalogos, Respuesta respuesta, int noRespuesta) {
        initialize(respuesta);
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

    public String opcionMultipleEspecificarOtro(List<Kcatalogo> catalogos, Respuesta respuesta, int noRespuesta) {
        initialize(respuesta);
        display = pregunta.getIdPreguntaRef() == 0 ? "block" : "none";
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
        fragmentoPregunta = "<div id=\"preguntas[" + noRespuesta + "].idPregunta_omeo\" "
                + " style=\"display:" + display + "\">"
                + pregunta.getCabeceraPregunta();
        part.append(fragmentoPregunta);
        for (Kcatalogo catalogo : catalogos) {
            checked = respuestaNormal.contains(catalogo.getDescripcion()) ? "checked" : "";
            fragmentoPregunta = "<section class=\"row\">\n"
                    + "  <div class=\"col-md-8\">\n"
                    + "  <div class=\"form-group\">\n"
                    + "  <input type=\"checkbox\" id=\"respuestas[" + noRespuesta + "].respuesta\" "
                    + "  onclick=\"getOmeo(\'" + catalogo.getDescripcion() + "'," + noRespuesta + ")\" "
                    + "  value=\"" + catalogo.getDescripcion() + " "
                    + "  name=\"respuestas[" + noRespuesta + "].respuesta\" "
                    + "  aria-label=\"Checkbox for following text input\" " + checked + ">\n"
                    + "  <label for=\"respuestas[" + noRespuesta + "].respuesta\">"
                    + catalogo.getDescripcion() + "</label>\n"
                    + "  </div>\n"
                    + "  </div>\n"
                    + "  </section>";
            part.append(fragmentoPregunta);
        }
        display = !respuestaEspecifica.equals("") ? "block" : "none";
        fragmentoPregunta = " <div class=\"col-md-8\">\n"
                + "    <div class=\"form-group\">\n"
                + " <input type=\"text\" style=\"display:" + display + "\" "
                + " id=\"respuestas[" + noRespuesta + "].respuestaEspecifica\" "
                + " name=\"respuestas[" + noRespuesta + "].respuestaEspecifica\" "
                + " class=\"form-control\" maxlength=\"" + maxLenght + "\" " + validaNumericos
                + " value=\"" + respuestaEspecifica + "\" placeholder=\"Especificar\">"
                + "</div></div></div></div><br/></div>";
        part.append(fragmentoPregunta);
        return part.toString();
    }

    public String listaDesplegableSinEspecificar(List<Kcatalogo> catalogos, Respuesta respuesta, int noRespuesta) {
        initialize(respuesta);
        fragmentoPregunta = pregunta.getCabeceraPregunta()
                + " <section class=\"row\">\n"
                + "          <div class=\"col-md-8\">\n"
                + "  <select class=\"form-control\" onchange=\"getSino(this," + noRespuesta + ")\" "
                + "  name=\"respuestas[" + noRespuesta + "].respuesta\" "
                + "  id=\"respuestas[" + noRespuesta + "].respuesta\" value=\"" + respuestaNormal
                + "\" required>\n";
        part.append(fragmentoPregunta);
        for (Kcatalogo catalogo : catalogos) {
            selected = respuestaNormal.equalsIgnoreCase(catalogo.getDescripcion()) ? "selected" : "";
            fragmentoPregunta = " <option value=\"" + catalogo.getDescripcion() + "\" " + selected + ">" + catalogo.getDescripcion()
                    + "</option>\n";
            part.append(fragmentoPregunta);
        }
        fragmentoPregunta = " </select> <div class=\"invalid-feedback\">La opcion es requerida</div>\n"
                + "          </div>\n"
                + "        </section></div></div><br/>";
        part.append(fragmentoPregunta);
        return part.toString();
    }

    public String listaDesplegableEspecificarOtro(List<Kcatalogo> catalogos, Respuesta respuesta, int noRespuesta) {
        initialize(respuesta);
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

    public String opcionTextoLibre(Respuesta respuesta, int noRespuesta) {
        initialize(respuesta);
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
    
    public static String siguiente() {
        return "<input type=\"submit\" class=\"btn btn-success\" value=\"Siguiente\"></form>\n"
                + " </div> </div><br/>\n";
    }

    public static String finalizarEncuesta() {
        return "    <div>\n"
                + "      <section class=\"col-md-12\">\n"
                + "        <h4 class=\"text-center\">HAS TERMINADO LA ENCUESTA</h4>\n"
                + "        <p></p>\n"
                + "      </section>\n"
                + "    </section><br/>\n"
                + "  <form method=\"post\" action=\"../principal\" >\n"
                + "        <center><input type=\"submit\" class=\"btn btn-success\" value=\"Finalizar\">\n"
                + "        <a href=\"../principal\">Regresar</a>\n"
                + "        </center></form></div>\n"
                + "        <br/>\n"
                + "        <br/>\n";
    }

    public static String sinEncuesta() {
        return "    <div>\n"
                + "      <section class=\"col-md-12\">\n"
                + "        <h4 class=\"text-center\">NO HAY ENCUESTA</h4>\n"
                + "        <p></p>\n"
                + "      </section>\n"
                + "    </section><br/>\n"
                + "        <center>\n"
                + "        <a href=\"../encuestas/principal\">Regresar</a>\n"
                + "        </center></div>\n"
                + "        <br/>\n"
                + "        <br/>\n";
    }
    
    public static String htmlCapitulo(Pregunta pregunta, int noCapitulo) {
        return " <div>\n"
                + "    <div class=\"card mb-3 bg-light\" style=\"width: 100%;\"><div class=\"card-body\">"
                + "    <section class=\"row\">\n"
                + "      <div class=\"col-md-12\">\n"
                + "        <h2 class=\"text-center\">"
                + pregunta.getCuestionario().getCuestionario()
                + "        </h2>\n"
                + "        <h6 class=\"text-center\">Sistema de cuestionarios de inmuebles.</h5>\n"
                + "      </div>\n"
                + "    </section>\n"
                + "    </div></div><br />\n"
        //tab de capitulo
                + " <section class=\"col-md-12\">\n"
                + "        <h4 class=\"text-center\">"
                + pregunta.getCapitulo().getCapitulo()
                + "        </h4>\n"
                + "        <p></p>\n"
                + "      </section>\n"
                + "    </section><br/>"
                + " <form class=\"needs-validation\" "
                + "  novalidate method=\"post\" "
                + "  action=\"updateEncuesta/" + pregunta.getCuestionario().getIdCuestionario()
                + "/" + noCapitulo + "\" object=\"objectRespuestas\" >\n";
    }
    
    public static String htmlSubCapitulo(String subcapitulo) {
        return " <section class=\"row\">\n"
                + "      <section class=\"col-md-12\">\n"
                + "        <h5 class=\"text-center\">"
                + subcapitulo
                + "        </h5>\n"
                + "        <p></p>\n"
                + "      </section>\n"
                + "    </section>";
    }
    
    private void initialize(Respuesta respuesta){
        part = new StringBuilder();
        pregunta = respuesta.getPregunta();
        respuestaNormal = Objects.isNull(respuesta.getRespuesta()) ? "" : respuesta.getRespuesta();
        respuestaEspecifica = Objects.isNull(respuesta.getRespuestaEspecifica()) ? ""
                : respuesta.getRespuestaEspecifica();
        
        idRespuesta = respuesta.getIdRespuesta();
        idInmueble = respuesta.getInmueble().getIdInmueble();
        idCicloEscolar = respuesta.getCicloEscolar().getIdCicloEscolar();
        noUsuario = respuesta.getUsuarioRegistro().getNoUsuario();
        Operacion = respuesta.getIdRespuesta() != 0 ? 3 : 1;
        idRevision = respuesta.getIdRevision();
        observaciones = Objects.isNull(respuesta.getObservaciones()) ? ""
                : respuesta.getObservaciones();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.respuesta;

import com.app.inmuebles.cicloEscolar.CicloEscolar;
import com.app.inmuebles.inmueble.Inmueble;
import com.app.inmuebles.kcatalogo.Kcatalogo;
import com.app.inmuebles.kcatalogo.KcatalogoService;
import com.app.inmuebles.pregunta.Pregunta;
import com.app.inmuebles.util.SessionControl;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Edward Reyes
 */
@Component
public class RespuestaUtil {

    @Autowired
    SessionControl session;
    @Autowired
    private KcatalogoService kcatalogoService;
    String fragmentoPregunta,
            capituloAnterior = "",
            subCapituloAnterior = "",
            cabeceraPregunta,
            validaNumericos = "",
            catalogoPregunta,
            claveCatalogo,
            respuestaNormal,
            respuestaEspecifica,
            observaciones,
            display,
            checked,
            selected;

    int noPregunta,
            noCapitulo,
            noRespuesta,
            maxLenght = 0,
            idRespuesta = 0,
            idInmueble = 0,
            idCicloEscolar = 0,
            noUsuario = 0,
            Operacion = 0,
            idRevision = 0;
    StringBuilder part;
    List<Kcatalogo> catalogos;
    public List<String> getPreguntasHtml(List<Pregunta> preguntas, List<Respuesta> respuestas) {
        noPregunta = 0;
        noRespuesta = 0;
        noCapitulo = 0;
        List<String> listaForms = new ArrayList<>();
        StringBuilder htmlPreguntas = new StringBuilder();

        if (preguntas.size() > 0) {

            for (Pregunta pregunta : preguntas) {

                Optional<Respuesta> respuestaOp = respuestas.stream()
                        .filter((res) -> res.getPregunta().getIdPregunta() == pregunta.getIdPregunta())
                        .findFirst();

                Respuesta respuesta = respuestaOp.orElse(new Respuesta());
                //inicializa variables de apoyo en caso de respuesta
                initializeVariablesRespuesta(respuesta);
                //contador numero de pregunta
                noPregunta++;
                //cabeceras y datos adicionales
                headerAndOther(pregunta);
                //titulo capitulo
                htmlPreguntas.append(capitulo(pregunta));
                //titulo subcapitulo
                htmlPreguntas.append(subcapitulo(pregunta));
                //obtiene cuerpo de pregunta
                htmlPreguntas.append(bodyPregunta(pregunta));
                //obtiene capitulo de pregunta anterior al finalizar.
                capituloAnterior = pregunta.getCapitulo().getCapitulo();
                //obtiene subcapitulo de pregunta anterior al finalizar (si es que lo tiene).
                subCapituloAnterior = Objects.isNull(pregunta.getSubCapitulo().getSubCapitulo())
                        ? "" : pregunta.getSubCapitulo().getSubCapitulo();
                //este bloque coloca el boton siguiente que finaliza el capitulo y abre el siguiente.
                if (!Objects.equals(capituloAnterior, preguntas.get(noPregunta < preguntas.size()
                        ? noPregunta : noRespuesta).getCapitulo().getCapitulo())) {
                    htmlPreguntas.append(siguiente());
                    listaForms.add(htmlPreguntas.toString());
                    htmlPreguntas = new StringBuilder();
                }
                noRespuesta++;
            }

            //parte final de la encuesta
            htmlPreguntas.append(finalizarEncuesta());
            listaForms.add(htmlPreguntas.toString());
        }
        //no hay cuestionario
        if (listaForms.size() < 1) {
            listaForms.add(sinEncuesta());
        }
        return listaForms;
    }

    private void headerAndOther(Pregunta pregunta) {
        catalogoPregunta = Objects.isNull(pregunta.getCatalogo()) ? "" : pregunta.getCatalogo();
        claveCatalogo = Objects.isNull(pregunta.getCatalogo()) ? ""
                : pregunta.getCuestionario().getIdCuestionario() + ""
                + pregunta.getCatalogo() + ""
                + pregunta.getClaveCatalogo();
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
    }

    private void initializeVariablesRespuesta(Respuesta respuesta) {
        idRespuesta = respuesta.getIdRespuesta();
        idInmueble = respuesta.getInmueble().getIdInmueble();
        idCicloEscolar = respuesta.getCicloEscolar().getIdCicloEscolar();
        noUsuario = respuesta.getUsuarioRegistro().getNoUsuario();
        Operacion = respuesta.getIdRespuesta() != 0 ? 3 : 1;
        idRevision = respuesta.getIdRevision();
        observaciones = Objects.isNull(respuesta.getObservaciones()) ? ""
                : respuesta.getObservaciones();
        respuestaNormal = Objects.isNull(respuesta.getRespuesta()) ? "" : respuesta.getRespuesta();
        respuestaEspecifica = Objects.isNull(respuesta.getRespuestaEspecifica()) ? ""
                : respuesta.getRespuestaEspecifica();
    }

    private String bodyPregunta(Pregunta pregunta) {
        part = new StringBuilder();
        String opcionMultiple = pregunta.getOpcionMultiple();
        String especificarxcatalogo = pregunta.getEspecificarxCatalogo();
        String especificarOtro = pregunta.getOtroEspecificar();
        String enCatalogo = pregunta.getEnCatalogo();
        if (Objects.equals(enCatalogo, "S"))
            catalogos = kcatalogoService.getRegistrosEncuesta(catalogoPregunta);

        //opciones multiple
        if (Objects.equals(opcionMultiple, "S")) {

            //opciones multiple sin especificar
            if (Objects.equals(especificarxcatalogo, "N") && Objects.equals(especificarOtro, "N"))
                part.append(opcionMultipleSinEspecificar(catalogos, pregunta));
            
            //opciones multiple especificar por catalogo
            else if (Objects.equals(especificarxcatalogo, "S") && Objects.equals(especificarOtro, "N"))
                part.append(opcionMultipleEspecificarCatalogo(catalogos, pregunta));
            

            //opciones multiple especificar por otro
            if (Objects.equals(especificarxcatalogo, "N") && Objects.equals(especificarOtro, "S"))
                part.append(opcionMultipleEspecificarOtro(catalogos, pregunta));
            

        } //No opcion multiple
        else {

            //No opcion multiple con catalogo
            if (Objects.equals(enCatalogo, "S")) {

                //No opcion multiple con catalogo especificando otro dato
                if (Objects.equals(especificarxcatalogo, "N") && Objects.equals(especificarOtro, "S"))
                    part.append(listaDesplegableEspecificarOtro(catalogos));
                //No opcion multiple con catalogo sin especificar
                else if (Objects.equals(especificarxcatalogo, "N") && Objects.equals(especificarOtro, "N"))
                    part.append(listaDesplegableSinEspecificar(catalogos));
                

            } //Sin opcion multiple sin catalogo (abierto)
            else part.append(opcionTextoLibre(pregunta));
        }

        return part.toString();
    }

    private String capitulo(Pregunta pregunta) {
        part = new StringBuilder();
        if (!Objects.equals(capituloAnterior, pregunta.getCapitulo().getCapitulo())) {
           noCapitulo++;
        fragmentoPregunta = " <div>\n"
                + "    <div class=\"card mb-3 bg-light\" style=\"width: 100%;\"><div class=\"card-body\">"
                + "    <section class=\"row\">\n"
                + "      <div class=\"col-md-12\">\n"
                + "        <h2 class=\"text-center\">"
                + pregunta.getCuestionario().getCuestionario()
                + "        </h2>\n"
                + "        <h6 class=\"text-center\">Sistema de cuestionarios de inmuebles.</h5>\n"
                + "      </div>\n"
                + "    </section>\n"
                + "    </div></div><br />\n";
        part.append(fragmentoPregunta);
        //tab de capitulo
        fragmentoPregunta = " <section class=\"col-md-12\">\n"
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
        }else{ fragmentoPregunta="";}
        part.append(fragmentoPregunta);
        return part.toString();
    }

    private String subcapitulo(Pregunta pregunta) {
    if (!Objects.equals(subCapituloAnterior, pregunta.getSubCapitulo().getSubCapitulo())
                        && Objects.nonNull(pregunta.getSubCapitulo().getSubCapitulo())) {
        fragmentoPregunta = "<section class=\"row\">\n"
                + "      <section class=\"col-md-12\">\n"
                + "        <h5 class=\"text-center\">"
                + pregunta.getSubCapitulo().getSubCapitulo()
                + "        </h5>\n"
                + "        <p></p>\n"
                + "      </section>\n"
                + "    </section>";
        }else{ fragmentoPregunta="";}
        return fragmentoPregunta;
    }

    private static String siguiente() {
        return "<input type=\"submit\" class=\"btn btn-success\" value=\"Siguiente\"></form>\n"
                + " </div> </div><br/>\n";
    }

    private static String finalizarEncuesta() {
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
    
    private String opcionMultipleSinEspecificar(List<Kcatalogo> catalogos, Pregunta pregunta) {
        part = new StringBuilder();
        fragmentoPregunta = cabeceraPregunta;
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

    private String opcionMultipleEspecificarCatalogo(List<Kcatalogo> catalogos, Pregunta pregunta) {
        part = new StringBuilder();
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
        fragmentoPregunta = cabeceraPregunta
                + " <section class=\"row\">\n"
                + "  <div class=\"col-md-8\">\n"
                + "  <div class=\"form-group\">\n";
        part.append(fragmentoPregunta);
        for (Kcatalogo catalogo : catalogos) {
            checked = respuestaNormal.contains(catalogo.getDescripcion()) ? "checked" : "";
            fragmentoPregunta = " <div class=\"input-group mb-3\" id=\"check\">\n"
                    + "  <div class=\"input-group-prepend\">\n"
                    + "    <div class=\"input-group-text\">\n"
                    + "     <input type=\"checkbox\" value=\"" + catalogo.getDescripcion() + "\" "
                    + "      name=\"respuestas[" + noRespuesta + "].respuesta\" "
                    + "      aria-label=\"Checkbox for following text input\" " + checked + "> \n"
                    + "     <label for=\"respuestas[" + noRespuesta + "].respuesta\">"
                    + catalogo.getDescripcion() + ": *</label>\n"
                    + "    </div>\n"
                    + " </div>\n"
                    + " <input type=\"text\" name=\"respuestas[" + noRespuesta + "].respuestaEspecifica\" "
                    + " class=\"form-control\" maxlength=\"" + maxLenght + "\" " + validaNumericos
                    + " value=\"" + respuestaEspecifica + "\" aria-label=\"Text input with checkbox\">\n"
                    + " </div>\n";
            part.append(fragmentoPregunta);
        }
        fragmentoPregunta = "  </div>\n"
                + " </div>\n"
                + " </section></div></div><br/>";
        part.append(fragmentoPregunta);
        return part.toString();
    }

    private String opcionMultipleEspecificarOtro(List<Kcatalogo> catalogos, Pregunta pregunta) {
        part = new StringBuilder();
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
                + cabeceraPregunta;
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

    private String listaDesplegableSinEspecificar(List<Kcatalogo> catalogos) {
        part = new StringBuilder();
        fragmentoPregunta = cabeceraPregunta
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

    private String listaDesplegableEspecificarOtro(List<Kcatalogo> catalogos) {
        part = new StringBuilder();
        fragmentoPregunta = cabeceraPregunta
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

    private String opcionTextoLibre(Pregunta pregunta) {

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
        fragmentoPregunta = cabeceraPregunta
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

    private static String sinEncuesta() {
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
    
    public Respuesta respuestaMapeada(Respuesta res) {

        Objects.nonNull(res);
        if (res.getPregunta().getOpcionMultiple().equals("S") && Objects.nonNull(res.getRespuesta())) {
            res.setRespuesta(replaceCaracter(res.getRespuesta(), 1));
        }
        res.setRespuesta(Objects.nonNull(res.getRespuesta()) ? res.getRespuesta().toUpperCase() : res.getRespuesta());
        res.setRespuestaEspecifica(Objects.nonNull(res.getRespuestaEspecifica()) ? res.getRespuestaEspecifica().toUpperCase() : res.getRespuestaEspecifica());
        res.setObservaciones(null);
        res.setUsuarioModif(session.getUsuario());
        res.setUsuarioRegistro(session.getUsuario());
        Inmueble inm = new Inmueble();
        inm.setIdInmueble(1);
        res.setInmueble(inm);
        res.setCicloEscolar(new CicloEscolar(1, 1, "2019", "2020", "20/01/2020", 1));
        return res;
    }

    private String replaceCaracter(String caracter, int opcion) {
        Objects.nonNull(caracter);
        return opcion == 1 ? caracter.replaceAll(",", "|") : caracter.replaceAll("|", ",");
    }

}

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
public class FormBody {

    public static String headerAndOther(Respuesta respuesta) {
        int idRespuesta = respuesta.getIdRespuesta();
        int idInmueble = respuesta.getInmueble().getIdInmueble();
        int idCicloEscolar = respuesta.getCicloEscolar().getIdCicloEscolar();
        int noUsuario = respuesta.getUsuarioRegistro().getNoUsuario();
        int Operacion = respuesta.getIdRespuesta() != 0 ? 3 : 1;
        int idRevision = respuesta.getIdRevision();
        String observaciones = Objects.isNull(respuesta.getObservaciones()) ? ""
                : respuesta.getObservaciones();
        int noRespuesta = respuesta.getNoRespuesta();
        Pregunta pregunta = respuesta.getPregunta();
        String catalogoPregunta = Objects.isNull(pregunta.getCatalogo()) ? "" : pregunta.getCatalogo();
        String claveCatalogo = Objects.isNull(pregunta.getCatalogo()) ? ""
                : pregunta.getCuestionario().getIdCuestionario() + ""
                + pregunta.getCatalogo() + ""
                + pregunta.getClaveCatalogo();
        int noPregunta = noRespuesta + 1;
        String cabeceraPregunta = " <div class=\"card bg-light mb-3\" style=\"width: 100%;\"><div class=\"card-body\">"
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

    public static String capitulo(Pregunta pregunta, int noCapitulo) {
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

    public static String subCapitulo(String subcapitulo) {
        return " <section class=\"row\">\n"
                + "      <section class=\"col-md-12\">\n"
                + "        <h5 class=\"text-center\">"
                + subcapitulo
                + "        </h5>\n"
                + "        <p></p>\n"
                + "      </section>\n"
                + "    </section>";
    }

}

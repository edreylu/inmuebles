/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.objectHtml;

import com.app.riife.cicloEscolar.CicloEscolar;
import com.app.riife.inicio.SessionComponent;
import com.app.riife.inmueble.Inmueble;
import com.app.riife.pregunta.Pregunta;
import com.app.riife.kcatalogo.KcatalogoService;
import com.app.riife.respuesta.Respuesta;
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
public class HtmlComponent {

    private final SessionComponent session;
    private final KcatalogoService kcatalogoService;
    private String capituloAnterior = "";
    private String subCapituloAnterior = "";
    private int noCapitulo;

    @Autowired
    public HtmlComponent(SessionComponent session, KcatalogoService kcatalogoService) {
        this.session = session;
        this.kcatalogoService = kcatalogoService;
    }
    

    public List<String> getPreguntasHtml(List<Pregunta> preguntas, List<Respuesta> respuestas) {
        String cabeceraPregunta;
        int noPregunta = 0;
        int noRespuesta = 0;
        noCapitulo = 0;
        List<String> listForms = new ArrayList<>();
        StringBuilder htmlPreguntas = new StringBuilder();

        if (preguntas.size() > 0) {

            for (Pregunta pregunta : preguntas) {

                Optional<Respuesta> respuestaOp = respuestas.stream()
                        .filter((res) -> res.getPregunta().getIdPregunta() == pregunta.getIdPregunta())
                        .findFirst();

                Respuesta respuesta = respuestaOp.orElse(new Respuesta());
                respuesta.setPregunta(pregunta);
                respuesta.setNoRespuesta(noRespuesta);
                noPregunta++;
                cabeceraPregunta = FormBody.headerAndOther(respuesta);
                pregunta.setCabeceraPregunta(cabeceraPregunta);
                respuesta.setPregunta(pregunta);
                htmlPreguntas.append(capitulo(pregunta));
                htmlPreguntas.append(subcapitulo(pregunta));
                htmlPreguntas.append(bodyPregunta(respuesta));
                capituloAnterior = pregunta.getCapitulo().getCapitulo();
                subCapituloAnterior = Objects.isNull(pregunta.getSubCapitulo().getSubCapitulo())
                        ? "" : pregunta.getSubCapitulo().getSubCapitulo();
                //este bloque coloca el boton siguiente que finaliza el capitulo y abre el siguiente.
                if (!Objects.equals(capituloAnterior, preguntas.get(noPregunta < preguntas.size()
                        ? noPregunta : noRespuesta).getCapitulo().getCapitulo())) {
                    htmlPreguntas.append(FormBody.siguiente());
                    listForms.add(htmlPreguntas.toString());
                    htmlPreguntas = new StringBuilder();
                }
                noRespuesta++;
            }

            //parte final de la encuesta
            htmlPreguntas.append(FormBody.finalizarEncuesta());
            listForms.add(htmlPreguntas.toString());
        }
        //no hay cuestionario
        if (listForms.size() < 1) {
            listForms.add(FormBody.sinEncuesta());
        }
        return listForms;
    }

    private String bodyPregunta(Respuesta respuesta) {
        Pregunta pregunta = respuesta.getPregunta();
        boolean isOpcionMultiple = Objects.equals(pregunta.getOpcionMultiple(), "S");
        boolean isEspecificarxcatalogo = Objects.equals(pregunta.getEspecificarxCatalogo(), "S");
        boolean isEspecificarOtro = Objects.equals(pregunta.getOtroEspecificar(), "S");
        boolean isEnCatalogo = Objects.equals(pregunta.getEnCatalogo(), "S");
        //Sin opcion multiple sin catalogo (abierto) (default)
        ObjectHtml objectHtml = new OpcionTextoLibre();

        //opciones multiple
        if (isOpcionMultiple) {

            if (!isEspecificarxcatalogo && !isEspecificarOtro) {
                objectHtml = new OpcionMultipleSinEspecificar(kcatalogoService);
            } 
            else if (isEspecificarxcatalogo && !isEspecificarOtro) {
                objectHtml = new OpcionMultipleEspecificarCatalogo(kcatalogoService);
            }

            if (!isEspecificarxcatalogo && isEspecificarOtro) {
                objectHtml = new OpcionMultipleEspecificarOtro(kcatalogoService);
            }

        } //No opcion multiple
        else {

            //No opcion multiple con catalogo
            if (isEnCatalogo) {

                if (!isEspecificarxcatalogo && isEspecificarOtro) {
                    objectHtml = new ListaDesplegableEspecificarOtro(kcatalogoService);
                } else if (!isEspecificarxcatalogo && !isEspecificarOtro) {
                    objectHtml = new ListaDesplegableSinEspecificar(kcatalogoService);
                }

            } 
        }
        String object = objectHtml.create(respuesta);
        return object;
    }

    private String capitulo(Pregunta pregunta) {
        String fragmentoPregunta = "";
        boolean isNewCapitulo =!Objects.equals(capituloAnterior, pregunta.getCapitulo().getCapitulo());
        if (isNewCapitulo) {
            noCapitulo++;
            fragmentoPregunta = FormBody.capitulo(pregunta, noCapitulo);
        }
        return fragmentoPregunta;
    }

    private String subcapitulo(Pregunta pregunta) {
        String fragmentoPregunta = "";
        String subCapitulo = pregunta.getSubCapitulo().getSubCapitulo();
        boolean isNewSubCapitulo = !Objects.equals(subCapituloAnterior, subCapitulo) && Objects.nonNull(subCapitulo);
        if (isNewSubCapitulo) {
            fragmentoPregunta = FormBody.subCapitulo(subCapitulo);
        }
        return fragmentoPregunta;
    }

    public Respuesta respuestaMap(Respuesta res) {

        Objects.requireNonNull(res);
        if (res.getPregunta().getOpcionMultiple().equals("S")) {
            if (Objects.nonNull(res.getRespuesta())) {
                res.setRespuesta(replaceCaracter(res.getRespuesta(), 1));
            }
            if (Objects.nonNull(res.getRespuestaEspecifica())) {
                res.setRespuestaEspecifica(replaceCaracter(res.getRespuestaEspecifica(), 1));
            }
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
        return opcion == 1 ? caracter.replaceAll(",", "\\|") : caracter.replaceAll("\\|", ",");
    }

}

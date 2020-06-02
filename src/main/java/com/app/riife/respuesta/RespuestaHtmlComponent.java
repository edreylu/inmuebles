/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.respuesta;

import com.app.riife.cicloEscolar.CicloEscolar;
import com.app.riife.inmueble.Inmueble;
import com.app.riife.kcatalogo.Kcatalogo;
import com.app.riife.kcatalogo.KcatalogoService;
import com.app.riife.pregunta.Pregunta;
import com.app.riife.inicio.SessionControl;
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
public class RespuestaHtmlComponent {

    @Autowired
    SessionControl session;
    @Autowired
    private KcatalogoService kcatalogoService;
    @Autowired
    private OpcionHtmlComponent opcionHtml;
    private String fragmentoPregunta,
            capituloAnterior = "",
            subCapituloAnterior = "",
            cabeceraPregunta,
            catalogoPregunta;
    private int noPregunta,
            noCapitulo,
            noRespuesta;
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
                respuesta.setPregunta(pregunta);
                //contador numero de pregunta
                noPregunta++;
                //cabeceras y datos adicionales
                cabeceraPregunta=opcionHtml.headerAndOther(respuesta,noRespuesta);
                pregunta.setCabeceraPregunta(cabeceraPregunta);
                respuesta.setPregunta(pregunta);
                //titulo capitulo
                htmlPreguntas.append(capitulo(pregunta));
                //titulo subcapitulo
                htmlPreguntas.append(subcapitulo(pregunta));
                //obtiene cuerpo de pregunta
                htmlPreguntas.append(bodyPregunta(respuesta, noRespuesta));
                //obtiene capitulo de pregunta anterior al finalizar.
                capituloAnterior = pregunta.getCapitulo().getCapitulo();
                //obtiene subcapitulo de pregunta anterior al finalizar (si es que lo tiene).
                subCapituloAnterior = Objects.isNull(pregunta.getSubCapitulo().getSubCapitulo())
                        ? "" : pregunta.getSubCapitulo().getSubCapitulo();
                //este bloque coloca el boton siguiente que finaliza el capitulo y abre el siguiente.
                if (!Objects.equals(capituloAnterior, preguntas.get(noPregunta < preguntas.size()
                        ? noPregunta : noRespuesta).getCapitulo().getCapitulo())) {
                    htmlPreguntas.append(OpcionHtmlComponent.siguiente());
                    listaForms.add(htmlPreguntas.toString());
                    htmlPreguntas = new StringBuilder();
                }
                noRespuesta++;
            }

            //parte final de la encuesta
            htmlPreguntas.append(OpcionHtmlComponent.finalizarEncuesta());
            listaForms.add(htmlPreguntas.toString());
        }
        //no hay cuestionario
        if (listaForms.size() < 1) {
            listaForms.add(OpcionHtmlComponent.sinEncuesta());
        }
        return listaForms;
    }
    
    private String bodyPregunta(Respuesta respuesta, int noRespuesta) {
        part = new StringBuilder();
        Pregunta pregunta = respuesta.getPregunta();
        catalogoPregunta = Objects.isNull(pregunta.getCatalogo()) ? "" : pregunta.getCatalogo();
        String opcionMultiple = pregunta.getOpcionMultiple();
        String especificarxcatalogo = pregunta.getEspecificarxCatalogo();
        String especificarOtro = pregunta.getOtroEspecificar();
        String enCatalogo = pregunta.getEnCatalogo();
        if (Objects.equals(enCatalogo, "S"))
            catalogos = kcatalogoService.listCatalogoEncuesta(catalogoPregunta);
        //opciones multiple
        if (Objects.equals(opcionMultiple, "S")) {

            //opciones multiple sin especificar
            if (Objects.equals(especificarxcatalogo, "N") && Objects.equals(especificarOtro, "N"))
                part.append(opcionHtml.opcionMultipleSinEspecificar(catalogos, respuesta, noRespuesta));
            
            //opciones multiple especificar por catalogo
            else if (Objects.equals(especificarxcatalogo, "S") && Objects.equals(especificarOtro, "N"))
                part.append(opcionHtml.opcionMultipleEspecificarCatalogo(catalogos, respuesta, noRespuesta));
            

            //opciones multiple especificar por otro
            if (Objects.equals(especificarxcatalogo, "N") && Objects.equals(especificarOtro, "S"))
                part.append(opcionHtml.opcionMultipleEspecificarOtro(catalogos, respuesta, noRespuesta));
            

        } //No opcion multiple
        else {

            //No opcion multiple con catalogo
            if (Objects.equals(enCatalogo, "S")) {

                //No opcion multiple con catalogo especificando otro dato
                if (Objects.equals(especificarxcatalogo, "N") && Objects.equals(especificarOtro, "S"))
                    part.append(opcionHtml.listaDesplegableEspecificarOtro(catalogos,respuesta, noRespuesta));
                //No opcion multiple con catalogo sin especificar
                else if (Objects.equals(especificarxcatalogo, "N") && Objects.equals(especificarOtro, "N"))
                    part.append(opcionHtml.listaDesplegableSinEspecificar(catalogos,respuesta, noRespuesta));
                

            } //Sin opcion multiple sin catalogo (abierto)
            else part.append(opcionHtml.opcionTextoLibre(respuesta, noRespuesta));
        }

        return part.toString();
    }

    private String capitulo(Pregunta pregunta) {
        part = new StringBuilder();
        if (!Objects.equals(capituloAnterior, pregunta.getCapitulo().getCapitulo())) {
           noCapitulo++;
           fragmentoPregunta = OpcionHtmlComponent.htmlCapitulo(pregunta, noCapitulo);
        }else{ fragmentoPregunta="";}
        part.append(fragmentoPregunta);
        return part.toString();
    }

    private String subcapitulo(Pregunta pregunta) {
    String subCapitulo = pregunta.getSubCapitulo().getSubCapitulo();
    if (!Objects.equals(subCapituloAnterior, subCapitulo) && Objects.nonNull(subCapitulo)) {
           fragmentoPregunta =  OpcionHtmlComponent.htmlSubCapitulo(subCapitulo);
        }else{ fragmentoPregunta="";}
        return fragmentoPregunta;
    }

    public Respuesta respuestaMap(Respuesta res) {

        Objects.requireNonNull(res);
        if (res.getPregunta().getOpcionMultiple().equals("S")) {
            if(Objects.nonNull(res.getRespuesta())){
            res.setRespuesta(replaceCaracter(res.getRespuesta(), 1));
            }
            if(Objects.nonNull(res.getRespuestaEspecifica())){
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

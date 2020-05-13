/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.respuesta;

import com.app.inmuebles.util.SessionControl;
import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.util.Mensaje;
import com.app.inmuebles.pregunta.Pregunta;
import com.app.inmuebles.util.Procedure;
import com.app.inmuebles.cuestionario.CuestionarioService;
import com.app.inmuebles.pregunta.PreguntaService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author usuario
 */
@Controller
public class RespuestaControl {

    @Autowired
    private SessionControl session;
    private final RespuestaService encuestaService;
    private final CuestionarioService cuestionarioService;
    private final PreguntaService preguntaService;
    private final RespuestaService respuestaService;
    @Autowired
    private RespuestaUtil respuestaUtil;
    private List<Cuestionario> cuestionarios;
    private List<Pregunta> preguntas;
    private List<Respuesta> respuestas;
    private List<Respuesta> respuestasToSend;
    private List<String> forms;
    private ObjectRespuestas objectRespuestas;
    private String html;
    private int idCapitulo;
    private final Mensaje msg = new Mensaje();

    @Autowired
    public RespuestaControl(RespuestaService encuestaService, CuestionarioService cuestionarioService, 
            PreguntaService preguntaService, RespuestaService respuestaService) {
        this.encuestaService = encuestaService;
        this.cuestionarioService = cuestionarioService;
        this.preguntaService = preguntaService;
        this.respuestaService = respuestaService;
    }
    
    

    @GetMapping("encuestas/principal")
    public String listar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("lista", cuestionarios);
        return session.url("encuestas/principal");
    }

    @GetMapping(value = "encuestas/responder/{id}")
    public String responder(@PathVariable("id") int id, Model model) {
        idCapitulo = 0;
        preguntas = preguntaService.listPreguntasById(id);
        respuestas = respuestaService.listRespuestasByIdAndUsuario(id, session.getUsuario().getNoUsuario());

        forms = obtenerEncuesta();
        return "redirect:/encuestas/respuestas";
    }

    @PostMapping(value = "encuestas/updateEncuesta/{id}/{idCapitulo}")
    public String responder(@PathVariable("id") int id, @PathVariable("idCapitulo") int idCap,
            @ModelAttribute("preguntasRespuestas") ObjectRespuestas respuestasEnTurno) {

        respuestasToSend = respuestasEnTurno.getRespuestas()
                .stream()
                .filter(respuesta -> respuesta.getPregunta().getIdPregunta() != 0)
                .map(respuestaUtil::respuestaMap)
                .collect(Collectors.toList());

        Procedure proc = encuestaService.ActRespuesta(respuestasToSend);
        if (proc.getError() != -1) {
            System.out.println("CORRECTO!: " + proc.getMensaje());
        } else {
            idCap--;
            System.err.println("NO SE CONCLUYO " + proc.getMensaje());
        }
        idCapitulo = idCap;
        return "redirect:/encuestas/respuestas";
    }

    @GetMapping(value = "encuestas/respuestas")
    public String siguiente(Model model) {
        html = forms.get(idCapitulo);
        objectRespuestas = new ObjectRespuestas();
        model.addAttribute("htmlEncuesta", html);
        model.addAttribute("objectRespuestas", objectRespuestas);
        return session.url("encuestas/responder");
    }

    public List<String> obtenerEncuesta() {
        Objects.requireNonNull(preguntas);
        List<String> listaForms = respuestaUtil.getPreguntasHtml(preguntas, respuestas);
        return listaForms;
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.respuesta;

import com.app.riife.inicio.SessionControl;
import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.util.Mensaje;
import com.app.riife.util.Procedure;
import com.app.riife.cuestionario.CuestionarioService;
import java.util.List;
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
    private final RespuestaService respuestaService;
    private List<Cuestionario> cuestionarios;
    private List<String> forms;
    private ObjectRespuestas objectRespuestas;
    private String html;
    private int idCapitulo;
    private final Mensaje msg = new Mensaje();

    @Autowired
    public RespuestaControl(RespuestaService encuestaService, CuestionarioService cuestionarioService, 
            RespuestaService respuestaService) {
        this.encuestaService = encuestaService;
        this.cuestionarioService = cuestionarioService;
        this.respuestaService = respuestaService;
    }
    

    @GetMapping("encuestas")
    public String listar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("lista", cuestionarios);
        return session.url("encuestas/principal");
    }

    @GetMapping(value = "encuestas/responder/{id}")
    public String responder(@PathVariable("id") int id, Model model) {
        idCapitulo = 0;
        forms = respuestaService.getForms(id, session.getUsuario().getNoUsuario());
        return session.url("redirect:/encuestas/respuestas");
    }

    @PostMapping(value = "encuestas/updateEncuesta/{id}/{idCapitulo}")
    public String responder(@PathVariable("id") int id, @PathVariable("idCapitulo") int idCap,
            @ModelAttribute("preguntasRespuestas") ObjectRespuestas respuestasEnTurno) {
        Procedure proc = encuestaService.ActRespuesta(respuestasEnTurno);
        if (proc.getError() != -1) {
            System.out.println("CORRECTO!: " + proc.getMensaje());
        } else {
            idCap--;
            System.err.println("NO SE CONCLUYO " + proc.getMensaje());
        }
        idCapitulo = idCap;
        return session.url("redirect:/encuestas/respuestas");
    }

    @GetMapping(value = "encuestas/respuestas")
    public String siguiente(Model model) {
        html = forms.get(idCapitulo);
        objectRespuestas = new ObjectRespuestas();
        model.addAttribute("htmlEncuesta", html);
        model.addAttribute("objectRespuestas", objectRespuestas);
        return session.url("encuestas/responder");
    }

}
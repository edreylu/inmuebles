/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.respuesta;

import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.util.Procedure;
import com.app.riife.cuestionario.CuestionarioService;
import com.app.riife.inicio.SessionComponent;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.annotation.SessionScope;

/**
 *
 * @author usuario
 */
@Controller
@SessionScope
public class RespuestaControl {

    private final SessionComponent session;
    private final CuestionarioService cuestionarioService;
    private final RespuestaService respuestaService;
    private List<Cuestionario> cuestionarios;
    private List<String> forms;
    private ObjectRespuestas objectRespuestas;
    private String html;
    private int idCapitulo;

    @Autowired
    public RespuestaControl(SessionComponent session, CuestionarioService cuestionarioService, RespuestaService respuestaService) {
        this.session = session;
        this.cuestionarioService = cuestionarioService;
        this.respuestaService = respuestaService;
    }
    

    @GetMapping("encuestas")
    public String listar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("lista", cuestionarios);
        return "encuestas/principal";
    }

    @GetMapping(value = "encuestas/responder/{id}")
    public String responder(@PathVariable("id") int id, Model model) {
        idCapitulo = 0;
        forms = respuestaService.getForms(id, session.getUsuario().getNoUsuario());
        return "redirect:/encuestas/respuestas";
    }

    @PostMapping(value = "encuestas/updateEncuesta/{id}/{idCapitulo}")
    public String responder(@PathVariable("id") int id, @PathVariable("idCapitulo") int idCap,
            @ModelAttribute("preguntasRespuestas") ObjectRespuestas respuestasEnTurno) {
        Procedure proc = respuestaService.ActRespuesta(respuestasEnTurno);
        idCapitulo = proc.getError() != -1 ? idCap : idCap--;
        return "redirect:/encuestas/respuestas";
    }

    @GetMapping(value = "encuestas/respuestas")
    public String siguiente(Model model) {
        if(Objects.nonNull(session.getUsuario())){
        html = forms.get(idCapitulo);
        objectRespuestas = new ObjectRespuestas();
        model.addAttribute("htmlEncuesta", html);
        model.addAttribute("objectRespuestas", objectRespuestas);
        return "encuestas/responder";
        }else{
        return "";
        }
    }

}
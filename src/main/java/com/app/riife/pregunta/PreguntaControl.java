/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.pregunta;

import com.app.riife.capitulo.Capitulo;
import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.util.Mensaje;
import com.app.riife.subCapitulo.SubCapitulo;
import com.app.riife.capitulo.CapituloService;
import com.app.riife.cuestionario.CuestionarioService;
import com.app.riife.inicio.SessionComponent;
import com.app.riife.kcatalogo.KcatalogoService;
import com.app.riife.subCapitulo.SubCapituloService;
import com.app.riife.usuario.Usuario;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author usuario
 */
@Controller
public class PreguntaControl {

    private final SessionComponent session;
    private final PreguntaService preguntaService;
    private final CuestionarioService cuestionarioService;
    private final CapituloService capituloService;
    private final SubCapituloService subCapituloService;
    private final KcatalogoService kcatalogoService;
    private List<Pregunta> preguntas;
    private List<Cuestionario> cuestionarios;
    private List<Capitulo> capitulos;
    private List<SubCapitulo> subcapitulos;
    private List<String> catalogos;
    private Pregunta pregunta;
    private final Mensaje msg = new Mensaje();

    @Autowired
    public PreguntaControl(SessionComponent session, PreguntaService preguntaService, 
            CuestionarioService cuestionarioService, CapituloService capituloService, 
            SubCapituloService subCapituloService, KcatalogoService kcatalogoService) {
        this.session = session;
        this.preguntaService = preguntaService;
        this.cuestionarioService = cuestionarioService;
        this.capituloService = capituloService;
        this.subCapituloService = subCapituloService;
        this.kcatalogoService = kcatalogoService;
    }
    

    @GetMapping("preguntas")
    public String listar(Model model) {
        preguntas = preguntaService.listAll();
        model.addAttribute("lista", preguntas);
        return "preguntas/principal";
    }

    @GetMapping("preguntas/agregar")
    public String agregar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("cuestionarios", cuestionarios);
        model.addAttribute(new Pregunta());
        return "preguntas/agregar";
    }

    @PostMapping(value = "preguntas/add")
    public String agregar(Pregunta pregunta, RedirectAttributes redirectAttrs) {
        pregunta.setUsuarioRegistro(new Usuario(session.noUsuarioActivo()));
        pregunta = Pregunta.preguntaAddAjustes(pregunta);
        System.out.println(pregunta.toString());
        msg.crearMensaje(preguntaService.add(pregunta), redirectAttrs);
        
        return "redirect:/preguntas";
    }

    @GetMapping(value = "preguntas/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        
        pregunta = preguntaService.get(id);
        String validUrl = "redirect:/preguntas";
        if(Objects.nonNull(pregunta)){
        cuestionarios = cuestionarioService.listAll();
        capitulos = capituloService.listByCuestionario(pregunta.getCuestionario().getIdCuestionario());
        subcapitulos = subCapituloService.listByIdCuestionario(pregunta.getCuestionario().getIdCuestionario());
        catalogos = Objects.equals(pregunta.getEnCatalogo(),"S") ? kcatalogoService.listOnlyCatalogo(): null;
        
        model.addAttribute("cuestionarios", cuestionarios);
        model.addAttribute("capitulos", capitulos);
        model.addAttribute("subcapitulos", subcapitulos);
        model.addAttribute("catalogos", catalogos);
        model.addAttribute("pregunta", pregunta);
        validUrl = "preguntas/editar";
        }
        return validUrl;
    }

    @PostMapping(value = "preguntas/update/{id}")
    public String editar(@PathVariable("id") int id, Pregunta pregunta, RedirectAttributes redirectAttrs) {
        
        pregunta.setIdPregunta(id);
        pregunta.setUsuarioModif(new Usuario(session.noUsuarioActivo()));
        pregunta = Pregunta.preguntaEditAjustes(pregunta);
        System.out.println(pregunta.toString());
        msg.crearMensaje(preguntaService.update(pregunta), redirectAttrs);
        
        return "redirect:/preguntas";
    }

    @GetMapping("preguntas/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus,
            RedirectAttributes redirectAttrs) {
        msg.crearMensaje(preguntaService.delete(id, idestatus), redirectAttrs);
        
        return "redirect:/preguntas";
    }

}

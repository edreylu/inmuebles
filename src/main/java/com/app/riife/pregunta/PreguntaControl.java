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
import com.app.riife.inicio.SessionControl;
import com.app.riife.cuestionario.CuestionarioService;
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

    @Autowired
    private SessionControl session;
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
    public PreguntaControl(PreguntaService preguntaService, CuestionarioService cuestionarioService, 
            CapituloService capituloService, SubCapituloService subCapituloService, KcatalogoService kcatalogoService) {
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
        return session.url("preguntas/principal");
    }

    @GetMapping("preguntas/agregar")
    public String agregar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("cuestionarios", cuestionarios);
        model.addAttribute(new Pregunta());
        return session.url("preguntas/agregar");
    }

    @PostMapping(value = "preguntas/add")
    public String agregar(Pregunta pregunta, RedirectAttributes redirectAttrs) {
        pregunta.setUsuarioRegistro(new Usuario(session.noUsuarioActivo()));
        pregunta = Pregunta.preguntaAddAjustes(pregunta);
        System.out.println(pregunta.toString());
        msg.crearMensaje(preguntaService.addPregunta(pregunta), redirectAttrs);
        
        return "redirect:/preguntas";
    }

    @GetMapping(value = "preguntas/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        
        pregunta = preguntaService.getPregunta(id);
        String validUrl = "redirect:/preguntas";
        if(Objects.nonNull(pregunta)){
        cuestionarios = cuestionarioService.listAll();
        capitulos = capituloService.listByCuestionario(pregunta.getCuestionario().getIdCuestionario());
        subcapitulos = subCapituloService.listByCuestionario(pregunta.getCuestionario().getIdCuestionario());
        catalogos = Objects.equals(pregunta.getEnCatalogo(),"S") ? kcatalogoService.listOnlyCatalogo(): null;
        
        model.addAttribute("cuestionarios", cuestionarios);
        model.addAttribute("capitulos", capitulos);
        model.addAttribute("subcapitulos", subcapitulos);
        model.addAttribute("catalogos", catalogos);
        model.addAttribute("pregunta", pregunta);
        validUrl = "preguntas/editar";
        }
        return session.url(validUrl);
    }

    @PostMapping(value = "preguntas/update/{id}")
    public String editar(@PathVariable("id") int id, Pregunta pregunta, RedirectAttributes redirectAttrs) {
        
        pregunta.setIdPregunta(id);
        pregunta.setUsuarioModif(new Usuario(session.noUsuarioActivo()));
        pregunta = Pregunta.preguntaEditAjustes(pregunta);
        System.out.println(pregunta.toString());
        msg.crearMensaje(preguntaService.editPregunta(pregunta), redirectAttrs);
        
        return "redirect:/preguntas";
    }

    @GetMapping("preguntas/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus,
            RedirectAttributes redirectAttrs) {
        msg.crearMensaje(preguntaService.deletePregunta(id, idestatus), redirectAttrs);
        
        return "redirect:/preguntas";
    }

    @GetMapping("preguntas/refreshCapitulos/{id}/{idPregunta}")
    public String getCapitulos(@PathVariable("id") int id, @PathVariable("idPregunta") int idPregunta, Model model) {
        System.out.println("valor pasado como pasametro: " + id);
        capitulos = capituloService.listByCuestionario(id);
        if (idPregunta != 0) {
            model.addAttribute("pregunta", pregunta);
        }
        model.addAttribute("capitulos", capitulos);

        String vista = idPregunta == 0 ? "preguntas/agregar" : "preguntas/editar";
        return vista + " :: #capitulos";
    }

    //Refresh de combos
    @GetMapping("preguntas/refreshSubCapitulos/{id}/{idPregunta}")
    public String getSubCapitulos(@PathVariable("id") int id, @PathVariable("idPregunta") int idPregunta, Model model) {
        System.out.println("valor pasado como pasametro: " + id);
        subcapitulos = subCapituloService.listByCuestionario(id);
        if (idPregunta != 0) {
            model.addAttribute("pregunta", pregunta);
        }
        model.addAttribute("subcapitulos", subcapitulos);
        String vista = idPregunta == 0 ? "preguntas/agregar" : "preguntas/editar";
        return vista + " :: #subcapitulos";
    }

    @GetMapping("preguntas/refreshEnCatalogo/{id}/{idPregunta}")
    public String getRefreshEnCatalogo(Model model, @PathVariable("id") String id, 
            @PathVariable("idPregunta") int idPregunta) {
        System.out.println("valor de idPregunta y id: " + idPregunta + ":" + id);
        catalogos = Objects.equals(id,"S") ? kcatalogoService.listOnlyCatalogo(): null;
        if (idPregunta != 0) {
            pregunta.setEnCatalogo(id);
            model.addAttribute("pregunta", pregunta);
        } else {
            model.addAttribute("enCatalogo", id);
        }
        model.addAttribute("catalogos", catalogos);
        String vista = idPregunta == 0 ? "preguntas/agregar" : "preguntas/editar";
        return vista + " :: #opcionesTodas";
    }

    @GetMapping("preguntas/refreshEnCatalogoEspecificar/{id}/{idPregunta}")
    public String getRefreshEnCatalogoEspecificar(Model model, @PathVariable("id") String id, 
            @PathVariable("idPregunta") int idPregunta) {
        System.out.println("valor de tipo y id: " + idPregunta + ":" + id);
        if (idPregunta != 0) {
            pregunta.setEspecificarPor(id);
            model.addAttribute("pregunta", pregunta);
        } else {
            model.addAttribute("especificarPor", id);
        }

        String vista = idPregunta == 0 ? "preguntas/agregar" : "preguntas/editar";
        return vista + " :: #opcionesEnCatalogo";
    }

    @GetMapping("preguntas/refreshTodo/{id}/{idPregunta}")
    public String getRefreshTodo(Model model, @PathVariable("id") String id, 
            @PathVariable("idPregunta") int idPregunta) {
        System.out.println("valor de tipo y id: " + idPregunta + ":" + id);
        catalogos = Objects.equals(id,"S") ? kcatalogoService.listOnlyCatalogo(): null;
        if (idPregunta != 0) {
            pregunta.setEnCatalogo(id);
            model.addAttribute("pregunta", pregunta);
        } else {
            model.addAttribute("enCatalogo", id);
        }
        model.addAttribute("catalogos", catalogos);
        String vista = idPregunta == 0 ? "preguntas/agregar" : "preguntas/editar";
        return vista + " :: #opcionesTodas";
    }

}

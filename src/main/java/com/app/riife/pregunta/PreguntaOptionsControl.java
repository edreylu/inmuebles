/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.pregunta;

import com.app.riife.capitulo.Capitulo;
import com.app.riife.capitulo.CapituloService;
import com.app.riife.kcatalogo.KcatalogoService;
import com.app.riife.subCapitulo.SubCapitulo;
import com.app.riife.subCapitulo.SubCapituloService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Edward Reyes
 */
@Controller
public class PreguntaOptionsControl {
    
    private final PreguntaService preguntaService;
    private final CapituloService capituloService;
    private final SubCapituloService subCapituloService;
    private final KcatalogoService kcatalogoService;
    private List<Capitulo> capitulos;
    private List<SubCapitulo> subcapitulos;
    private List<String> catalogos;
    private Pregunta pregunta;
    
    @Autowired
    public PreguntaOptionsControl(CapituloService capituloService, SubCapituloService subCapituloService, 
            KcatalogoService kcatalogoService, PreguntaService preguntaService) {
        this.capituloService = capituloService;
        this.subCapituloService = subCapituloService;
        this.kcatalogoService = kcatalogoService;
        this.preguntaService = preguntaService;
    }
    
    @GetMapping("preguntas/refreshCapitulos/{id}/{idPregunta}")
    public String getCapitulos(@PathVariable("id") int id, @PathVariable("idPregunta") int idPregunta, Model model) {
        System.out.println("valor pasado como pasametro: " + id);
        capitulos = capituloService.listByCuestionario(id);
        if (idPregunta != 0) {
            pregunta = Objects.isNull(pregunta) ? preguntaService.get(idPregunta) : pregunta;
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
        subcapitulos = subCapituloService.listByIdCuestionario(id);
        if (idPregunta != 0) {
            pregunta = Objects.isNull(pregunta) ? preguntaService.get(idPregunta) : pregunta;
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
            pregunta = Objects.isNull(pregunta) ? preguntaService.get(idPregunta) : pregunta;
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
            pregunta = Objects.isNull(pregunta) ? preguntaService.get(idPregunta) : pregunta;
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
            pregunta = Objects.isNull(pregunta) ? preguntaService.get(idPregunta) : pregunta;
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

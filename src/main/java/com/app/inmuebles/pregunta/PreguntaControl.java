/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.pregunta;

import com.app.inmuebles.capitulo.Capitulo;
import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.util.Mensaje;
import com.app.inmuebles.pregunta.Pregunta;
import com.app.inmuebles.subCapitulo.SubCapitulo;
import com.app.inmuebles.usuario.Usuario;
import com.app.inmuebles.capitulo.CapituloService;
import com.app.inmuebles.util.SessionControl;
import com.app.inmuebles.cuestionario.CuestionarioService;
import com.app.inmuebles.kcatalogo.KcatalogoService;
import com.app.inmuebles.pregunta.PreguntaService;
import com.app.inmuebles.subCapitulo.SubCapituloService;
import com.app.inmuebles.usuario.UsuarioService;
import java.util.List;
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
    SessionControl session;
    @Autowired
    private PreguntaService preguntaService;
    @Autowired
    private CuestionarioService cuestionarioService;
    @Autowired
    private CapituloService capituloService;
    @Autowired
    private SubCapituloService subCapituloService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private KcatalogoService kcatalogoService;
    List<Pregunta> datos;
    List<Cuestionario> cuestionarios;
    List<Capitulo> capitulos;
    List<SubCapitulo> subcapitulos;
    List<String> catalogos;
    Pregunta pregunta;
    List lista;
    int id;
    Mensaje msg = new Mensaje();

    @GetMapping("preguntas/principal")
    public String listar(Model model) {
        datos = preguntaService.listAll();
        model.addAttribute("lista", datos);
        return session.url("preguntas/principal");
    }

    @GetMapping("preguntas/agregar")
    public String agregar(Model model) {
        cuestionarios = cuestionarioService.listAll();
        model.addAttribute("cuestionarios", cuestionarios);
        Pregunta pregunta = new Pregunta();
        pregunta.setEnCatalogo("N");
        pregunta.setEspecificarxCatalogo("N");
        pregunta.setOtroEspecificar("N");
        pregunta.setSubirImagen("N");
        model.addAttribute(pregunta);
        return session.url("preguntas/agregar");
    }

    @PostMapping(value = "preguntas/add")
    public String agregar(Pregunta pr, RedirectAttributes redirectAttrs) {

        Usuario usuarioRegistro = new Usuario();
        usuarioRegistro.setNoUsuario(session.getUsuario().getNoUsuario());
        pr.setUsuarioRegistro(usuarioRegistro);
        if (pr.getEspecificarPor().equals("C")) {
            pr.setEspecificarxCatalogo("S");
        } else if (pr.getEspecificarPor().equals("O")) {
            pr.setOtroEspecificar("S");
        }
        System.out.println(pr.getEspecificarPor() + "," + pr.getCuestionario().getIdCuestionario() + ","
                + pr.getIdPregunta() + ","
                + pr.getPregunta().toUpperCase() + ","
                + pr.getCapitulo().getIdCapitulo() + ","
                + pr.getSubCapitulo().getIdSubCapitulo() + ","
                + pr.getOrdenMostrar() + ","
                + pr.getInciso() + ","
                + pr.getOpcion() + ","
                + pr.getInstruccionesLlenado() + ","
                + pr.getOpcionMultiple() + ","
                + pr.getEnCatalogo() + ","
                + pr.getCatalogo() + ","
                + pr.getClaveCatalogo() + ","
                + pr.getEspecificarxCatalogo() + ","
                + pr.getTipoDeDatoxCatalogo() + ","
                + pr.getLongitudMaximaxCatalogo() + ","
                + pr.getDecimalesLongMaxCat() + ","
                + pr.getSubirImagen() + ","
                + pr.getOtroEspecificar() + ","
                + pr.getTipoDeDatoOtro() + ","
                + pr.getLongitudMaximaOtro() + ","
                + pr.getDecimalesLongMaxOtro());
        int valor = preguntaService.addPregunta(pr);
        if (valor >= 1) {
            msg.success("Agregado correctamente", redirectAttrs);
            System.out.println("se agrego registro: " + valor);
        } else {
            msg.danger("No se pudo agregar", redirectAttrs);
            System.err.println("no se agrego registro");
        }
        return "redirect:/preguntas/principal";
    }

    @GetMapping(value = "preguntas/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model) {
        pregunta = preguntaService.getPregunta(id);
        cuestionarios = cuestionarioService.listAll();
        capitulos = capituloService.getRegistrosPorCuestionario(pregunta.getCuestionario().getIdCuestionario());
        subcapitulos = subCapituloService.getRegistrosPorCuestionario(pregunta.getCuestionario().getIdCuestionario());
        catalogos = pregunta.getEnCatalogo().equalsIgnoreCase("S") ? kcatalogoService.getRegistrosLista() : null;
        if (pregunta.getEspecificarxCatalogo().equals("S")) {
            pregunta.setEspecificarPor("C");
        } else if (pregunta.getOtroEspecificar().equals("S")) {
            pregunta.setEspecificarPor("O");
        }
        model.addAttribute("cuestionarios", cuestionarios);
        model.addAttribute("capitulos", capitulos);
        model.addAttribute("subcapitulos", subcapitulos);
        model.addAttribute("catalogos", catalogos);
        model.addAttribute("pregunta", pregunta);
        return session.url("preguntas/editar");
    }

    @PostMapping(value = "preguntas/update/{id}")
    public String editar(@PathVariable("id") int id, Pregunta pr, RedirectAttributes redirectAttrs) {

        Usuario usuarioModif = new Usuario();
        usuarioModif.setNoUsuario(session.getUsuario().getNoUsuario());
        pr.setUsuarioModif(usuarioModif);
        pr.setIdPregunta(id);
        pr.setEspecificarPor(pr.getEspecificarPor() == null ? "O" : pr.getEspecificarPor());
        pr.setOtroEspecificar("N");
        pr.setEspecificarxCatalogo("N");
        if (pr.getEspecificarPor().equals("C")) {
            pr.setEspecificarxCatalogo("S");
        } else if (pr.getEspecificarPor().equals("O")) {
            pr.setOtroEspecificar("S");
        }
        System.out.println(pr.getEspecificarPor() + "," + pr.getCuestionario().getIdCuestionario() + ","
                + pr.getIdPregunta() + ","
                + pr.getPregunta().toUpperCase() + ","
                + pr.getCapitulo().getIdCapitulo() + ","
                + pr.getSubCapitulo().getIdSubCapitulo() + ","
                + pr.getOrdenMostrar() + ","
                + pr.getInciso() + ","
                + pr.getOpcion() + ","
                + pr.getInstruccionesLlenado() + ","
                + pr.getOpcionMultiple() + ","
                + pr.getEnCatalogo() + ","
                + pr.getCatalogo() + ","
                + pr.getClaveCatalogo() + ","
                + pr.getEspecificarxCatalogo() + ","
                + pr.getTipoDeDatoxCatalogo() + ","
                + pr.getLongitudMaximaxCatalogo() + ","
                + pr.getDecimalesLongMaxCat() + ","
                + pr.getSubirImagen() + ","
                + pr.getOtroEspecificar() + ","
                + pr.getTipoDeDatoOtro() + ","
                + pr.getLongitudMaximaOtro() + ","
                + pr.getDecimalesLongMaxOtro());
        int valor = preguntaService.editPregunta(pr);
        if (valor >= 1) {
            msg.success("Editado correctamente", redirectAttrs);
            System.out.println("se edito registro: " + valor);
        } else {
            msg.danger("No se pudo editar", redirectAttrs);
            System.err.println("no se edito registro");
        }
        return "redirect:/preguntas/principal";
    }

    @GetMapping("preguntas/eliminar/{id}/{idestatus}")
    public String eliminar(@PathVariable("id") int id, @PathVariable("idestatus") int idestatus,
            RedirectAttributes redirectAttrs) {
        int valor = preguntaService.deletePregunta(id, idestatus);
        if (valor >= 1) {
            msg.success("Ejecutado correctamente: se " + (idestatus == 1 ? "Activó" : "Inactivo") + " registro", redirectAttrs);
            System.out.println("se " + (idestatus == 1 ? "Activó" : "Inactivo") + " registro: " + id);
        } else {
            msg.danger("No se pudo ejecutar", redirectAttrs);
            System.err.println("no se elimino registro");
        }
        return "redirect:/preguntas/principal";
    }

    @GetMapping("preguntas/refreshCapitulos/{id}/{idPregunta}")
    public String getCapitulos(@PathVariable("id") int id, @PathVariable("idPregunta") int idPregunta, Model model) {
        System.out.println("valor pasado como pasametro: " + id);
        capitulos = capituloService.getRegistrosPorCuestionario(id);
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
        subcapitulos = subCapituloService.getRegistrosPorCuestionario(id);
        if (idPregunta != 0) {
            model.addAttribute("pregunta", pregunta);
        }
        model.addAttribute("subcapitulos", subcapitulos);
        String vista = idPregunta == 0 ? "preguntas/agregar" : "preguntas/editar";
        return vista + " :: #subcapitulos";
    }

    @GetMapping("preguntas/refreshEnCatalogo/{id}/{idPregunta}")
    public String getRefreshEnCatalogo(Model model, @PathVariable("id") String id, @PathVariable("idPregunta") int idPregunta) {
        System.out.println("valor de idPregunta y id: " + idPregunta + ":" + id);
        catalogos = id.equalsIgnoreCase("S") ? kcatalogoService.getRegistrosLista() : null;
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
    public String getRefreshEnCatalogoEspecificar(Model model, @PathVariable("id") String id, @PathVariable("idPregunta") int idPregunta) {
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
    public String getRefreshTodo(Model model, @PathVariable("id") String id, @PathVariable("idPregunta") int idPregunta) {
        System.out.println("valor de tipo y id: " + idPregunta + ":" + id);
        catalogos = id.equalsIgnoreCase("S") ? kcatalogoService.getRegistrosLista() : null;
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

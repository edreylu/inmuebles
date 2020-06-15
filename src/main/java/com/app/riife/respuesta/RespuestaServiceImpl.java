/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.respuesta;

import com.app.riife.cuestionario.CuestionarioDAO;
import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.objectHtml.HtmlComponent;
import com.app.riife.pregunta.Pregunta;
import com.app.riife.pregunta.PreguntaService;
import com.app.riife.util.Procedure;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edward Reyes
 */
@Service
public class RespuestaServiceImpl implements RespuestaService{
    
    private final HtmlComponent htmlComponent;
    private final RespuestaDAO respuestaDAO;
    private final CuestionarioDAO cuestionarioDAO;
    private final PreguntaService preguntaService;
    private List<Respuesta> respuestasToSend;

    @Autowired
    public RespuestaServiceImpl(RespuestaDAO respuestaDAO, CuestionarioDAO cuestionarioDAO, 
            PreguntaService preguntaService, HtmlComponent htmlComponent) {
        this.respuestaDAO = respuestaDAO;
        this.cuestionarioDAO = cuestionarioDAO;
        this.preguntaService = preguntaService;
        this.htmlComponent = htmlComponent;
    }
    
    @Override
    public List<Cuestionario> listAll() {
        return cuestionarioDAO.getRecords();
    }
    
    @Override
    public Procedure ActRespuesta(ObjectRespuestas respuestas){
        respuestasToSend = respuestas.getRespuestas()
                .stream()
                .filter(respuesta -> respuesta.getPregunta().getIdPregunta() != 0)
                .map(htmlComponent::respuestaMap)
                .collect(Collectors.toList());
        respuestasToSend.forEach((res)-> System.out.println(res.getRespuesta()+
                ": respuesta especifica:"+res.getRespuestaEspecifica()));
        
    return respuestaDAO.ActRespuesta(respuestasToSend);
    }
    
    @Override
    public int countResponses(int idCuestionario, int noUsuario) {
    return respuestaDAO.countResponses(idCuestionario, noUsuario);
    }

    @Override
    public Respuesta getRespuesta(int idCuestionario, int noUsuario, int idPregunta) {
     return respuestaDAO.getRespuesta(idCuestionario, noUsuario,idPregunta);
    }

    @Override
    public List<String> getForms(int idCuestionario, int noUsuario) {
        List<Pregunta> preguntas = preguntaService.listByIdCuestionario(idCuestionario);
        List<Respuesta> respuestas = respuestaDAO.getRecordsRespuestas(idCuestionario, noUsuario);
        Objects.requireNonNull(preguntas);
        List<String> listaForms = htmlComponent.getPreguntasHtml(preguntas, respuestas);
        return listaForms;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.respuesta;

import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.util.Procedure;
import com.app.inmuebles.respuesta.Respuesta;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface RespuestaService {
    
    List<Cuestionario> listAll();
    
    Procedure ActRespuesta(List<Respuesta> respuestas);
    
    List<Respuesta> getRegistrosRespuestas(int idCuestionario, int noUsuario);
    
    Respuesta getRespuesta(int idCuestionario, int noUsuario, int idPregunta);
    
    int countResponses(int idCuestionario, int noUsuario);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.respuesta;

import com.app.riife.cuestionario.Cuestionario;
import com.app.riife.util.Procedure;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface RespuestaService {
    
    List<Cuestionario> listAll();
    
    Procedure ActRespuesta(List<Respuesta> respuestas);
    
    List<Respuesta> listRespuestasByIdAndUsuario(int idCuestionario, int noUsuario);
    
    Respuesta getRespuesta(int idCuestionario, int noUsuario, int idPregunta);
    
    int countResponses(int idCuestionario, int noUsuario);
    
}

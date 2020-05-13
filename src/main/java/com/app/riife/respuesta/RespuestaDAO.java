/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.respuesta;

import com.app.riife.util.Procedure;
import com.app.riife.respuesta.Respuesta;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface RespuestaDAO {

    Procedure ActRespuesta(List<Respuesta> respuestas);

    List<Respuesta> getRecordsRespuestas(int idCuestionario, int noUsuario);
    
    Respuesta getRespuesta(int idCuestionario, int noUsuario, int idPregunta);

    int countResponses(int idCuestionario, int noUsuario);
}

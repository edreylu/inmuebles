/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.pregunta;

import com.app.riife.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface PreguntaService {
    
    List<Pregunta> listAll();

    Mensaje addPregunta(Pregunta pr);

    Pregunta getPregunta(int id);

    Mensaje editPregunta(Pregunta pr);

    Mensaje deletePregunta(int id, int opcion);

    List<Pregunta> listPreguntasById(int idCuestionario);
}

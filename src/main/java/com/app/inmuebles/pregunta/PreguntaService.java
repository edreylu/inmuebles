/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.pregunta;

import com.app.inmuebles.pregunta.Pregunta;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface PreguntaService {
    
    List<Pregunta> listAll();

    int addPregunta(Pregunta pr);

    Pregunta getPregunta(int id);

    int editPregunta(Pregunta pr);

    int deletePregunta(int id, int opcion);

    List<Pregunta> getRegistrosPreguntas(int idCuestionario);
}

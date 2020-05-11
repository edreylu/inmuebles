/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.pregunta;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface PreguntaDAO {
    
    List<Pregunta> getRecords();

    int addPregunta(Pregunta pr);

    int editPregunta(Pregunta pr);

    Pregunta getPregunta(int id);

    int deletePregunta(int id, int opcion);
    
    List<Pregunta> getRecordsPreguntas(int idCuestionario);
}

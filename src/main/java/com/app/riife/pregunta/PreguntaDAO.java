/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.pregunta;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface PreguntaDAO {
    
    List<Pregunta> getRecords();

    int add(Pregunta pr);

    int update(Pregunta pr);

    Pregunta get(int id);

    int delete(int id, int opcion);
    
    List<Pregunta> getRecordsByIdCuestionario(int idCuestionario);
}

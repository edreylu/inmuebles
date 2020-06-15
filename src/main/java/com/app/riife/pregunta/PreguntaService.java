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

    Mensaje add(Pregunta pr);

    Pregunta get(int id);

    Mensaje update(Pregunta pr);

    Mensaje delete(int id, int opcion);

    List<Pregunta> listByIdCuestionario(int idCuestionario);
}

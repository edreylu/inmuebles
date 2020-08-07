/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.cuestionario;

import com.app.riife.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface CuestionarioService {
    
    List<Cuestionario> listAll();
    
    Mensaje add(Cuestionario cuestionario);
    
    Cuestionario get(int id);
    
    Mensaje update(Cuestionario cuestionario);
    
    Mensaje delete(int id, int opcion);
}

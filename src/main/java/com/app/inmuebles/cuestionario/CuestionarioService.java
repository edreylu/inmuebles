/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.cuestionario;

import com.app.inmuebles.cuestionario.Cuestionario;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface CuestionarioService {
    
    List<Cuestionario> listAll();
    
    int addCuestionario(Cuestionario cuestionario);
    
    Cuestionario getCuestionario(int id);
    
    int editCuestionario(Cuestionario cuestionario);
    
    int deleteCuestionario(int id, int opcion);
}

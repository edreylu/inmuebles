/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.capitulo;

import com.app.riife.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface CapituloService {
    
    List<Capitulo> listAll();
    
    Mensaje add(Capitulo capitulo);
    
    Capitulo get(int id);
    
    Mensaje update(Capitulo capitulo);
    
    Mensaje delete(int id, int opcion);
    
    List<Capitulo> listByCuestionario(int idCuestionario);
    
}

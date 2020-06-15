/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.subCapitulo;

import com.app.riife.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface SubCapituloService {
    
    List<SubCapitulo> listAll();

    Mensaje add(SubCapitulo subCapitulo);
    
    SubCapitulo get(int id);

    Mensaje update(SubCapitulo subCapitulo);

    Mensaje delete(int id, int opcion);

    List<SubCapitulo> listByIdCuestionario(int idCuestionario);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.capitulo;

import com.app.inmuebles.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface CapituloService {
    
    List<Capitulo> listAll();
    
    Mensaje addCapitulo(Capitulo capitulo);
    
    Capitulo getCapitulo(int id);
    
    Mensaje editCapitulo(Capitulo capitulo);
    
    Mensaje deleteCapitulo(int id, int opcion);
    
    List<Capitulo> listByCuestionario(int idCuestionario);
    
}

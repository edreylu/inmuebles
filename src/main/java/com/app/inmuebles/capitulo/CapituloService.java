/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.capitulo;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface CapituloService {
    
    List<Capitulo> listAll();
    
    int addCapitulo(Capitulo capitulo);
    
    Capitulo getCapitulo(int id);
    
    int editCapitulo(Capitulo capitulo);
    
    int deleteCapitulo(int id, int opcion);
    
    List<Capitulo> getRegistrosPorCuestionario(int idCuestionario);
    
}

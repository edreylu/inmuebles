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
public interface CapituloDAO {
    
    List<Capitulo> getRegistros();

    List<Capitulo> getRegistrosPorCuestionario(int idCuestionario);

    int addCapitulo(Capitulo ca);

    int editCapitulo(Capitulo ca);

    Capitulo getCapitulo(int id);

    int deleteCapitulo(int id, int opcion);
    
}

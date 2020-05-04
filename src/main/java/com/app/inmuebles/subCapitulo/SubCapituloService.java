/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.subCapitulo;

import com.app.inmuebles.subCapitulo.SubCapitulo;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface SubCapituloService {
    
    List<SubCapitulo> listAll();

    int addSubCapitulo(SubCapitulo subCapitulo);
    
    SubCapitulo getSubCapitulo(int id);

    int editSubCapitulo(SubCapitulo subCapitulo);

    int deleteSubCapitulo(int id, int opcion);

    List<SubCapitulo> getRegistrosPorCuestionario(int idCuestionario);
}

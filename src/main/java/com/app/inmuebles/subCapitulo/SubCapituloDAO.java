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
public interface SubCapituloDAO {
    
    List<SubCapitulo> getRegistros();

    List<SubCapitulo> getRegistrosPorCuestionario(int idCuestionario);

    int addSubCapitulo(SubCapitulo sc);

    int editSubCapitulo(SubCapitulo sc);

    SubCapitulo getSubCapitulo(int id);

    int deleteSubCapitulo(int id, int opcion);
    
}

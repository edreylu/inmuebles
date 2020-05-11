/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.subCapitulo;

import com.app.inmuebles.util.Mensaje;
import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface SubCapituloService {
    
    List<SubCapitulo> listAll();

    Mensaje addSubCapitulo(SubCapitulo subCapitulo);
    
    SubCapitulo getSubCapitulo(int id);

    Mensaje editSubCapitulo(SubCapitulo subCapitulo);

    Mensaje deleteSubCapitulo(int id, int opcion);

    List<SubCapitulo> listByCuestionario(int idCuestionario);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.subCapitulo;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface SubCapituloDAO {
    
    List<SubCapitulo> getRecords();

    List<SubCapitulo> getRecordsByIdCuestionario(int idCuestionario);

    int add(SubCapitulo sc);

    int update(SubCapitulo sc);

    SubCapitulo get(int id);

    int delete(int id, int opcion);
    
}

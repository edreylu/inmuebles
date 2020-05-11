/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.cuestionario;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface CuestionarioDAO {
    
    List<Cuestionario> getRecords();

    int addCuestionario(Cuestionario cu);

    int editCuestionario(Cuestionario cu);

    Cuestionario getCuestionario(int id);

    int deleteCuestionario(int id, int opcion);
}

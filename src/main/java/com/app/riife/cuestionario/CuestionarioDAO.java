/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.cuestionario;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface CuestionarioDAO {
    
    List<Cuestionario> getRecords();

    int add(Cuestionario cu);

    int update(Cuestionario cu);

    Cuestionario get(int id);

    int delete(int id, int opcion);
}

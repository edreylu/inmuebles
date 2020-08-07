/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.capitulo;

import java.util.List;

/**
 *
 * @author Edward Reyes
 */
public interface CapituloDAO {
    
    List<Capitulo> getRecords();

    List<Capitulo> getRecordsByCuestionario(int idCuestionario);

    int add(Capitulo ca);

    int update(Capitulo ca);

    Capitulo get(int id);

    int delete(int id, int opcion);
    
}

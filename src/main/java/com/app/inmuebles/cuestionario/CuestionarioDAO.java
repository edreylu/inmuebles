/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.cuestionario;

import com.app.inmuebles.cuestionario.Cuestionario;
import com.app.inmuebles.cuestionario.CuestionarioRowMapper;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Edward Reyes
 */
public interface CuestionarioDAO {
    
    List<Cuestionario> getRegistros();

    int addCuestionario(Cuestionario cu);

    int editCuestionario(Cuestionario cu);

    Cuestionario getCuestionario(int id);

    int deleteCuestionario(int id, int opcion);
}

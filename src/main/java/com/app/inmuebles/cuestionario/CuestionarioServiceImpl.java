/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.cuestionario;

import com.app.inmuebles.cuestionario.Cuestionario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CuestionarioServiceImpl implements CuestionarioService{

    @Autowired
    private CuestionarioDAO cuestionarioDAO;

    @Override
    public List<Cuestionario> listAll() {
        return cuestionarioDAO.getRegistros();
    }

    @Override
    public int addCuestionario(Cuestionario cuestionario) {
        return cuestionarioDAO.addCuestionario(cuestionario);
    }

    @Override
    public Cuestionario getCuestionario(int id) {
        return cuestionarioDAO.getCuestionario(id);
    }

    @Override
    public int editCuestionario(Cuestionario cuestionario) {
        return cuestionarioDAO.editCuestionario(cuestionario);
    }

    @Override
    public int deleteCuestionario(int id, int opcion) {
        return cuestionarioDAO.deleteCuestionario(id, opcion);
    }

}

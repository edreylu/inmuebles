/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.cuestionario;

import com.app.inmuebles.util.Mensaje;
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
    private Mensaje msg;

    @Override
    public List<Cuestionario> listAll() {
        return cuestionarioDAO.getRecords();
    }

    @Override
    public Mensaje addCuestionario(Cuestionario cuestionario) {
        int valor = cuestionarioDAO.addCuestionario(cuestionario);
        if (valor >= 1) {
            msg = new Mensaje("Agregado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public Cuestionario getCuestionario(int id) {
        return cuestionarioDAO.getCuestionario(id);
    }

    @Override
    public Mensaje editCuestionario(Cuestionario cuestionario) {
        int valor = cuestionarioDAO.editCuestionario(cuestionario);
        if (valor >= 1) {
            msg = new Mensaje("Editado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo editar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje deleteCuestionario(int id, int opcion) {
        int valor = cuestionarioDAO.deleteCuestionario(id, opcion);
        if (valor >= 1) {
            msg = new Mensaje("Ejecutado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo ejecutar", 2);
        }
        return msg;
    }

}

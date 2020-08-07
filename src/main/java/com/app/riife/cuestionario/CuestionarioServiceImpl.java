/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.cuestionario;

import com.app.riife.util.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CuestionarioServiceImpl implements CuestionarioService{
    
    private final CuestionarioDAO cuestionarioDAO;
    private Mensaje msg;
    
    @Autowired
    public CuestionarioServiceImpl(CuestionarioDAO cuestionarioDAO) {
        this.cuestionarioDAO = cuestionarioDAO;
    }

    @Override
    public List<Cuestionario> listAll() {
        return cuestionarioDAO.getRecords();
    }

    @Override
    public Mensaje add(Cuestionario cuestionario) {
        int valor = cuestionarioDAO.add(cuestionario);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Agregado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public Cuestionario get(int id) {
        return cuestionarioDAO.get(id);
    }

    @Override
    public Mensaje update(Cuestionario cuestionario) {
        int valor = cuestionarioDAO.update(cuestionario);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Editado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo editar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje delete(int id, int opcion) {
        int valor = cuestionarioDAO.delete(id, opcion);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Ejecutado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo ejecutar", 2);
        }
        return msg;
    }

}

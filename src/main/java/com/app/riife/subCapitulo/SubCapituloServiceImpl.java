/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.subCapitulo;

import com.app.riife.util.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class SubCapituloServiceImpl implements SubCapituloService{

    private final SubCapituloDAO subCapituloDAO;
    private Mensaje msg;

    @Autowired
    public SubCapituloServiceImpl(SubCapituloDAO subCapituloDAO) {
        this.subCapituloDAO = subCapituloDAO;
    }
    
    @Override
    public List<SubCapitulo> listAll() {
        return subCapituloDAO.getRecords();
    }

    @Override
    public Mensaje add(SubCapitulo subCapitulo) {
        int valor = subCapituloDAO.add(subCapitulo);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Agregado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public SubCapitulo get(int id) {
        return subCapituloDAO.get(id);
    }

    @Override
    public Mensaje update(SubCapitulo subCapitulo) {
        int valor = subCapituloDAO.update(subCapitulo);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Editado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo editar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje delete(int id, int opcion) {
        int valor = subCapituloDAO.delete(id, opcion);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Ejecutado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo ejecutar", 2);
        }
        return msg;
    }

    @Override
    public List<SubCapitulo> listByIdCuestionario(int idCuestionario) {
        return subCapituloDAO.getRecordsByIdCuestionario(idCuestionario);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.capitulo;

import com.app.riife.util.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CapituloServiceImpl implements CapituloService{

    private final CapituloDAO capituloDAO;
    private Mensaje msg;
    
    @Autowired
    public CapituloServiceImpl(CapituloDAO capituloDAO) {
        this.capituloDAO = capituloDAO;
    }
    
    @Override
    public List<Capitulo> listAll() {
        return capituloDAO.getRecords();
    }

    @Override
    public Mensaje addCapitulo(Capitulo capitulo) {
        int valor = capituloDAO.addCapitulo(capitulo);
        if (valor >= 1) {
            msg = new Mensaje("Agregado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public Capitulo getCapitulo(int id) {
        return capituloDAO.getCapitulo(id);
    }

    @Override
    public Mensaje editCapitulo(Capitulo capitulo) {
        int valor = capituloDAO.editCapitulo(capitulo);
        if (valor >= 1) {
            msg = new Mensaje("Editado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo editar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje deleteCapitulo(int id, int opcion) {
        int valor = capituloDAO.deleteCapitulo(id, opcion);
        if (valor >= 1) {
            msg = new Mensaje("Ejecutado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo ejecutar", 1);
        }
        return msg;
    }

    @Override
    public List<Capitulo> listByCuestionario(int idCuestionario) {
        return capituloDAO.getRecordsByCuestionario(idCuestionario);
    }

}

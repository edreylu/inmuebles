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
    public Mensaje add(Capitulo capitulo) {
        int valor = capituloDAO.add(capitulo);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Agregado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public Capitulo get(int id) {
        return capituloDAO.get(id);
    }

    @Override
    public Mensaje update(Capitulo capitulo) {
        int valor = capituloDAO.update(capitulo);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Editado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo editar", 2);
        }
        return msg;
    }

    @Override
    public Mensaje delete(int id, int opcion) {
        int valor = capituloDAO.delete(id, opcion);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Ejecutado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo ejecutar", 1);
        }
        return msg;
    }

    @Override
    public List<Capitulo> listByCuestionario(int idCuestionario) {
        return capituloDAO.getRecordsByCuestionario(idCuestionario);
    }

}

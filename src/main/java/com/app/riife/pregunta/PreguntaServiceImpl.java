/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.riife.pregunta;

import com.app.riife.util.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PreguntaServiceImpl implements PreguntaService{

    private final PreguntaDAO preguntaDAO;
    private Mensaje msg;

    @Autowired
    public PreguntaServiceImpl(PreguntaDAO preguntaDAO) {
        this.preguntaDAO = preguntaDAO;
    }
    
    @Override
    public List<Pregunta> listAll() {
        return preguntaDAO.getRecords();
    }

    @Override
    public Mensaje add(Pregunta pr) {
        int valor = preguntaDAO.add(pr);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Agregado correctamente", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public Pregunta get(int id) {
        return preguntaDAO.get(id);
    }

    @Override
    public Mensaje update(Pregunta pr) {
        int valor = preguntaDAO.update(pr);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Editado correctamente", 1);
            System.out.println("se edito registro: " + valor);
        } else {
            msg = Mensaje.CREATE("No se pudo editar", 2);
            System.err.println("no se edito registro");
        }
        return msg;
    }

    @Override
    public Mensaje delete(int id, int opcion) {
        int valor = preguntaDAO.delete(id, opcion);
        if (valor >= 1) {
            msg = Mensaje.CREATE("Ejecutado correctamente: se " 
                    + (opcion == 1 ? "Activó" : "Inactivo") + " registro", 1);
        } else {
            msg = Mensaje.CREATE("No se pudo ejecutar", 2);
        }
        return msg;
    }

    @Override
    public List<Pregunta> listByIdCuestionario(int idCuestionario) {
        return preguntaDAO.getRecordsByIdCuestionario(idCuestionario);
    }

}

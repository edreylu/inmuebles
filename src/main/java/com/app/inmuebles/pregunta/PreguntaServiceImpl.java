/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.inmuebles.pregunta;

import com.app.inmuebles.util.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PreguntaServiceImpl implements PreguntaService{

    @Autowired
    private PreguntaDAO preguntaDAO;
    private Mensaje msg;
    @Override
    public List<Pregunta> listAll() {
        return preguntaDAO.getRecords();
    }

    @Override
    public Mensaje addPregunta(Pregunta pr) {
        int valor = preguntaDAO.addPregunta(pr);
        if (valor >= 1) {
            msg = new Mensaje("Agregado correctamente", 1);
        } else {
            msg = new Mensaje("No se pudo agregar", 2);
        }
        return msg;
    }

    @Override
    public Pregunta getPregunta(int id) {
        return preguntaDAO.getPregunta(id);
    }

    @Override
    public Mensaje editPregunta(Pregunta pr) {
        int valor = preguntaDAO.editPregunta(pr);
        if (valor >= 1) {
            msg = new Mensaje("Editado correctamente", 1);
            System.out.println("se edito registro: " + valor);
        } else {
            msg = new Mensaje("No se pudo editar", 2);
            System.err.println("no se edito registro");
        }
        return msg;
    }

    @Override
    public Mensaje deletePregunta(int id, int opcion) {
        int valor = preguntaDAO.deletePregunta(id, opcion);
        if (valor >= 1) {
            msg = new Mensaje("Ejecutado correctamente: se " 
                    + (opcion == 1 ? "Activó" : "Inactivo") + " registro", 1);
        } else {
            msg = new Mensaje("No se pudo ejecutar", 2);
        }
        return msg;
    }

    @Override
    public List<Pregunta> listPreguntasById(int idCuestionario) {
        return preguntaDAO.getRecordsPreguntas(idCuestionario);
    }

}
